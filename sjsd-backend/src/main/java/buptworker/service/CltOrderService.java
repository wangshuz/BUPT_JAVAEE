package buptworker.service;

import buptworker.entity.CltOrder;

import java.util.List;

public interface CltOrderService {

    public List<CltOrder> getCltOrders(String userId);

    public void updateCltOrderStatus(String orderId, String status);

    public void deleteCltMchOrder(String orderId);

    public void actualDeliveryTime(String orderId, String realTime);
}
