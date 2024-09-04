package buptworker.service;

import buptworker.entity.MchOrder;

import java.util.List;

public interface MchOrderService {

    public List<MchOrder> getMchOrders(String mchId);

    public void updateOrderStatus(String mchId, String status);

    public void deleteMchOrder(String mchId);
}
