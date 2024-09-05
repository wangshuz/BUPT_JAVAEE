package buptworker.service;

import buptworker.entity.*;

import java.util.List;

public interface MchDataService {

    public List<SalesData> listSalesData(int merchantId);

    public List<Data> getCurData(int merchantId);

    public MerchantInfo getMerchantInfo(int merchantId);

    public ProductStats getProdStats(int merchantId);

    public OrderStats getMonthlyOrderStats(int merchantId);

}
