package cn.smbms.test;

import cn.smbms.beans.Provider;
import cn.smbms.dao.provider.ProviderDao;
import cn.smbms.util.MyBatiesUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class testProvider {
    @Test
    public  void test01() {
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        List<Provider> allProvider =
                sqlSession.getMapper(ProviderDao.class).getAllProvider();

        for ( Provider p : allProvider ) {
            System.out.println(p.toString());
        }


    }


}
