package buptworker.service.impl;

import buptworker.entity.MchOrder;
import buptworker.entity.MchOrderDetail;
import buptworker.entity.MchOrderItem;
import buptworker.mapper.MchOrderMapper;
import buptworker.service.MchOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MchOrderService_ implements MchOrderService {

    @Autowired
    private MchOrderMapper mchOrderMapper;

    @Override
    public List<MchOrder> getMchOrders(String mchId)
    {
        List<MchOrder> mchOrders = new ArrayList<MchOrder>();
        List<MchOrderDetail> mchOrderDetails =  mchOrderMapper.listMchOrderDetail(mchId);
        for (MchOrderDetail mchOrderDetail : mchOrderDetails) {
            List<MchOrderItem> mchOrderItems = mchOrderMapper.listMchOrderItem(mchOrderDetail.getOrderId());
            mchOrders.add(new MchOrder(mchOrderDetail, mchOrderItems));
        }
        return mchOrders;
    }

    @Override
    public void updateOrderStatus(String mchId, String status)
    {
        mchOrderMapper.updateOrderStatus(mchId, status);
    }

    @Override
    public void deleteMchOrder(String mchId)
    {
        mchOrderMapper.updateIsDeleted(mchId);
    }
}
