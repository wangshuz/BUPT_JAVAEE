package buptworker.service;

import buptworker.entity.SalesData;

import java.util.List;

public interface MchDataService {

    public List<SalesData> listSalesData(int merchantId);
}
