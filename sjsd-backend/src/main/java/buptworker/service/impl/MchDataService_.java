package buptworker.service.impl;

import buptworker.entity.*;
import buptworker.mapper.MchDataMapper;
import buptworker.service.MchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 21:02]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 21:02]
 * @updateRemark : [说明本次修改内容]
 */

@Component
public class MchDataService_ implements MchDataService {
    @Autowired
    public MchDataMapper mchDataMapper;
    @Override
    public List<SalesData> listSalesData(int merchantId) {
        return mchDataMapper.listSalesData(merchantId);
    }

    @Override
    public List<Data> getCurData(int merchantId) {
        SalesData salesData = mchDataMapper.getCurData(merchantId);
        if(salesData==null){
            salesData = new SalesData("",0,0,0,0);
        }
        List<Data> listData = new ArrayList<Data>();
        listData.add(new Data("营业额",(int)salesData.getTotalSalesAmount()));
        listData.add(new Data("总销量",salesData.getTotalSalesVolume()));
        listData.add(new Data("订单数",salesData.getTotalOrderCount()));
        listData.add(new Data("用户数",salesData.getTotalCustomerCount()));
        return listData;
    }

    @Override
    public MerchantInfo getMerchantInfo(int merchantId) {
        return mchDataMapper.getMerchantInfo(merchantId);
    }

    @Override
    public ProductStats getProdStats(int merchantId) {
        return  mchDataMapper.getProductStats(merchantId);
    }

    @Override
    public OrderStats getMonthlyOrderStats(int merchantId) {
        return mchDataMapper.getMonthlyOrderStats(merchantId);
    }
}
