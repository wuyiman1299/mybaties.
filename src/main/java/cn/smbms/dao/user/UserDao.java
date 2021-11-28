package cn.smbms.dao.user;

import cn.smbms.beans.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserDao {
    // 查询有多少用户
    public int getUserCount();

    // 查询所有用户的信息
    public List<User> getUserList();

    // 模糊查询用户的信息
    public  List<User> getUserListByName(String name );

    // 多个参数查询用户的信息
    public List<User> getUserListByNameAndRole( User user );

    public List<User> getUserListByNameAndRole2(HashMap<String , String>  map );

    // 查询用户的角色名称和基本信息
    public List<User> getUserListWithRoleByNameAndRole(User user);


    // 增加用户
    public int addUser(User user);

    // 修改用户信息
    public int updateUser( User user ) ;

    // 根据id修改用户的密码
    public int updatePwdById(@Param("id") Integer id , @Param("pwd") String pwd );

    // 根据id删除用户
    public int deleteUser(@Param("id") Integer id ) ;

    // 根据用户的角色id查询用户的列表
    public List<User> getUserListByRoleId(@Param("userRoleId") Integer roleId ) ;

    // 根据用户的id，查询用户相关的信息和其负责的人员地址
    public List<User> getUserListWithCusAddressById(@Param("id") Integer id);

    // in (1,2 ,3)
    public List<User> getUserListByRoleId_foreach( Integer [] ids );

    public List<User> getUserListByRoleId_list( List<Integer> roleList );

    public List<User> getUserListByRoleId_map ( HashMap<String,Object> map ) ;

    // 根据用户的任何一个信息，皆可查询到用户
    public List<User> getUserList_choose (@Param("userCode")  String userCode ,
                                          @Param("userName")  String userName ,
                                          @Param("gender")  Integer gender ,
                                          @Param("phone")  String phone ,
                                          @Param("address")  String address
    ) ;

    // 分页查询用户的信息
    public List<User> getUserList_page(@Param("from") Integer from ,@Param("pageSize") Integer pageSize ) ;

}
