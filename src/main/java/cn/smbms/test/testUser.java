package cn.smbms.test;

import cn.smbms.beans.Address;
import cn.smbms.beans.User;
import cn.smbms.dao.user.UserDao;
import cn.smbms.util.MyBatiesUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.JobOriginatingUserName;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class testUser {
    private static  Logger logger = Logger.getLogger(testUser.class);

    public static void main(String[] args) {
        try {
            // 1 读取配置文件
            InputStream is = Resources.getResourceAsStream("configuration.xml");
            // 2 创建工厂
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
            // 3 打开会话
            SqlSession sqlSession = factory.openSession();
            // 4 操作数据库
            int userCount = sqlSession.getMapper(UserDao.class).getUserCount();
            logger.debug("用户的个数为：" + userCount);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test() {
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        int userCount = sqlSession.getMapper(UserDao.class).getUserCount();
        MyBatiesUtil.closeSqlSession(sqlSession);
        logger.debug("用户数量：" + userCount);

    }

    @Test
    public void test01() {
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        List<User> userList = sqlSession.selectList("cn.smbms.dao.user.UserDao.getUserList");
        for ( User u : userList ) {
            System.out.println(u.toString());
        }
        MyBatiesUtil.closeSqlSession(sqlSession);

    }

    @Test
    public void test02() {
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        List<User> userList = sqlSession.getMapper(UserDao.class).getUserList();
        for ( User u : userList ) {
            System.out.println(u.toString());
        }
        MyBatiesUtil.closeSqlSession(sqlSession);
    }

    @Test
    public void testGetUserListByName() {
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        List<User> userList = sqlSession.getMapper(UserDao.class).getUserListByName("赵");
        for ( User u : userList ) {
            System.out.println(u.toString());
        }
        MyBatiesUtil.closeSqlSession(sqlSession);
    }

    @Test
    public void testGetUserListByNameAndRole() {
        User user = new User();
        //user.setUserName("赵");
        //user.setUserRole(3);
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        List<User> userList = sqlSession.getMapper(UserDao.class).getUserListByNameAndRole(user);
        for (User u : userList ) {
            System.out.println(u.toString());
        }
        MyBatiesUtil.closeSqlSession(sqlSession);

    }

    @Test
    public void testGetUserListByNameAndRole2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("userName" , "赵");
        map.put("userRole" , "3");
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        List<User> userList = sqlSession.getMapper(UserDao.class).getUserListByNameAndRole2(map);
        for (User u : userList ) {
            System.out.println(u.toString());
        }
        MyBatiesUtil.closeSqlSession(sqlSession);

    }

    @Test
    public void testGetUserListWithRoleByNameAndRole() {
        User user = new User();
        user.setUserName("赵");
        user.setUserRole(3);
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        List<User> userList = sqlSession.getMapper(UserDao.class).getUserListWithRoleByNameAndRole(user);
        for (User u : userList ) {
            System.out.println(u.toString());
        }
        MyBatiesUtil.closeSqlSession(sqlSession);


    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUserCode("dazhuzi");
        user.setUserName("大柱子");
        user.setUserPassword("123456");
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        sqlSession.getMapper(UserDao.class).addUser(user);
        sqlSession.commit();  // 需要手动提交
        MyBatiesUtil.closeSqlSession(sqlSession);

    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(16);
        user.setUserCode("adou");
        user.setUserName("阿斗");
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        sqlSession.getMapper(UserDao.class).updateUser(user);
        sqlSession.commit();
        MyBatiesUtil.closeSqlSession(sqlSession);
    }

    @Test
    public void testUpdatePwdById() {
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        sqlSession.getMapper(UserDao.class).updatePwdById(33 , "999999");
        sqlSession.commit();
        MyBatiesUtil.closeSqlSession(sqlSession);

    }
    @Test
    public void testDeletUser() {
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        sqlSession.getMapper(UserDao.class).deleteUser(2);
        sqlSession.commit();
        MyBatiesUtil.closeSqlSession(sqlSession);


    }

    @Test
    public void testGetUserListByRoleId() {
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        List<User> userList = sqlSession.getMapper(UserDao.class).getUserListByRoleId(2);
        for (User u : userList ) {
            System.out.println(u.getId() + " "+ u.getUserCode()+ " "+ u.getUserName() + " "+ u.getUserRole()
                    + " "+ u.getRole().getRoleCode() + " "+ u.getRole().getRoleName()
            );
        }
        MyBatiesUtil.closeSqlSession(sqlSession);

    }

    @Test
    public void testGetUserListWithCusAddressById() {
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        List<User> userList = sqlSession.getMapper(UserDao.class).getUserListWithCusAddressById(1);
        /*for (User u : userList ) {
            System.out.println( u.getId() + " " + u.getUserName() );
            for (Address a : u.getCusAddress() ) {
                System.out.println(a.getContact() + " " + a.getAddressDesc() );

            }
        }*/

        for ( User u : userList ) {
            System.out.println(u.toString());
            for ( Address a : u.getCusAddress() ) {
                System.out.println(a.toString());
            }
        }


    }


    @Test
    public void testGetUserListByRoleId_foreach() {
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        Integer [] a = {1,2};
        List<User> userList = sqlSession.getMapper(UserDao.class).getUserListByRoleId_foreach(a);
        for ( User u : userList ) {
            System.out.println( u.getId() + " " + u.getUserCode() +" " + u.getUserName() );
        }
        MyBatiesUtil.closeSqlSession(sqlSession);
    }

    @Test
    public void testGetUserListByRoleId_list(){
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        ArrayList<Integer> roleList = new ArrayList<>();
        roleList.add(1);
        roleList.add(2);
        List<User> userList = sqlSession.getMapper(UserDao.class).getUserListByRoleId_list(roleList);
        for ( User u : userList ) {
            System.out.println( u.getId() + " " + u.getUserCode() +" " + u.getUserName() );
        }
        MyBatiesUtil.closeSqlSession(sqlSession);

    }

    @Test
    public void  testGetUserListByRoleId_map () {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2) ;

        HashMap<String, Object> map = new HashMap<>();
        map.put("roleIds" , list );

        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        List<User> userList = sqlSession.getMapper(UserDao.class).getUserListByRoleId_map(map);
        for ( User u : userList ) {
            System.out.println( u.getId() + " " + u.getUserCode() +" " + u.getUserName() );
        }
        MyBatiesUtil.closeSqlSession(sqlSession);


    }

        @Test
        public void testGetUserList_choose () {
            SqlSession sqlSession = MyBatiesUtil.createSqlSession();
            List<User> userList = sqlSession.getMapper(UserDao.class).getUserList_choose
                    (null, null, null, "133", null);
            for ( User u : userList ) {
                System.out.println( u.getId() + " " + u.getUserCode() +" " + u.getUserName() );
            }
            MyBatiesUtil.closeSqlSession(sqlSession);

        }


        @Test
        public void testGetUserList_page() {
            SqlSession sqlSession = MyBatiesUtil.createSqlSession();
            List<User> userList = sqlSession.getMapper(UserDao.class).getUserList_page(1, 6);
            for ( User u : userList ) {
                System.out.println( u.getId() + " " + u.getUserCode() +" " + u.getUserName() );
            }
            MyBatiesUtil.closeSqlSession(sqlSession);
        }




}
