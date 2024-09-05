package buptworker.service.impl;

import buptworker.entity.CltOrder;
import buptworker.entity.CltOrderInfo;
import buptworker.entity.CltOrderItem;
import buptworker.mapper.CltOrderMapper;
import buptworker.service.CltOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CltOrderService_ implements CltOrderService {

    @Autowired
    private CltOrderMapper cltOrderMapper;

    @Override
    public List<CltOrder> getCltOrders(String userId)
    {
        List<CltOrder> cltOrders = new ArrayList<CltOrder>();
        List<CltOrderItem> cltOrderItems = cltOrderMapper.getCltOrderItems(userId);
        for (CltOrderItem cltOrderItem : cltOrderItems) {
            List<CltOrderInfo> cltOrderInfos = cltOrderMapper.getCltOrderInfos(cltOrderItem.getOrderId());
            cltOrders.add(new CltOrder(cltOrderItem, cltOrderInfos));
        }
        return cltOrders;
    }

    @Override
    public void updateCltOrderStatus(String orderId, String status)
    {
        cltOrderMapper.updateOrderStatus(orderId, status);
    }

    @Override
    public void deleteCltMchOrder(String orderId)
    {
        cltOrderMapper.deleteCltMchOrder(orderId);
    }



}
