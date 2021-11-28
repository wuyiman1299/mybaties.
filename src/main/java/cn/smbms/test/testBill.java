package cn.smbms.test;

import cn.smbms.beans.Bill;
import cn.smbms.dao.bill.BillDao;
import cn.smbms.util.MyBatiesUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class testBill {

    @Test
    public void test01() {
        Bill bill = new Bill();
        bill.setIsPayment(2);
        bill.setProductName("油");
        bill.setProviderId(7);
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        List<Bill> billList = sqlSession.getMapper(BillDao.class).getBillWithProviderName(bill);
        for (Bill b : billList) {
            System.out.println(b.toString());
        }
        MyBatiesUtil.closeSqlSession(sqlSession);

    }

    @Test
    public void  test02() {
        SqlSession sqlSession = MyBatiesUtil.createSqlSession();
        List<Bill> billList = sqlSession.getMapper(BillDao.class).getBillListByProductNameAndProvider("油", 7, 2);
        for ( Bill b : billList ) {
            System.out.println( b.getBillCode() + " " + b.getProductName() + " " +
                    b.getProvider().getProCode() + " " + b.getProvider().getProName() + " "
                    + b.getProvider().getProContact() + " " + b.getProvider().getProPhone() + " "
                    + b.getTotalPrice() + " " + b.getIsPayment()

                    );
        }
        MyBatiesUtil.closeSqlSession(sqlSession);



    }



}
