package cn.smbms.dao.bill;

import cn.smbms.beans.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillDao {

    // 查询包含供应商名字的订单 （跨表）
    public List<Bill> getBillWithProviderName( Bill bill ) ;

    public List<Bill> getBillListByProductNameAndProvider(@Param("productName") String productName ,
                                                          @Param("providerId") Integer ProviderId ,
                                                          @Param("isPaid") Integer isPaid);

}
