package cn.smbms.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatiesUtil {
    private static SqlSessionFactory factory;
    // 静态代码块，在类加载的时候，会立即调用
    static {
        try {
            // 1 读取配置文件
            InputStream is = Resources.getResourceAsStream("configuration.xml");
            // 2 创建工厂
            factory = new SqlSessionFactoryBuilder().build(is);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static  SqlSession createSqlSession() {
        // 3 打开会话
        return  factory.openSession();
    }
    public  static void closeSqlSession(SqlSession sqlSession) {
        if( null != sqlSession ) {
            sqlSession.close();
        }
    }




}
