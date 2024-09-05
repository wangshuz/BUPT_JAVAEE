package buptworker.service;

import buptworker.entity.Data;
import buptworker.entity.SalesData;

import java.util.List;

public interface MchDataService {

    public List<SalesData> listSalesData(int merchantId);

    public List<Data> getCurData(int merchantId);

}
