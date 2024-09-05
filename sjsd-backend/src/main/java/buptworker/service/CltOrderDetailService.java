package buptworker.service;

import buptworker.entity.CltOrderDetail;

import java.util.List;

public interface CltOrderDetailService {

    public CltOrderDetail getCltOrderDetail(String orderId);

    public void updateCltOrderDetailStatus(String orderId, String status);

    public void deleteCltDetailOrder(String orderId);
}
