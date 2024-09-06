package buptworker.service.impl;

import buptworker.entity.*;
import buptworker.mapper.CltOrderDetailMapper;
import buptworker.service.CltOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CltOrderDetailService_ implements CltOrderDetailService {

    @Autowired
    private CltOrderDetailMapper cltOrderDetailMapper;

    @Override
    public CltOrderDetail getCltOrderDetail(String orderId)
    {
        CltOrderDetailItem cltOrderDetailItems = cltOrderDetailMapper.getcltOrderDetailItemByOrderId(orderId);
        switch (cltOrderDetailItems.getState()) {
            case "1", "2", "3" -> cltOrderDetailItems.setState("1");
            case "4" -> cltOrderDetailItems.setState("2");
            case "5" -> cltOrderDetailItems.setState("3");
        }
        List<CltOrderDDetailInfo> cltOrderDetailInfos = cltOrderDetailMapper.listCltOrderDetailInfoByOrderId(orderId);
        return new CltOrderDetail(cltOrderDetailItems, cltOrderDetailInfos);
    }
    @Override
    public void updateCltOrderDetailStatus(String orderId, String status)
    {
        cltOrderDetailMapper.updateOrderDetailStatus(orderId, status);
    }
    @Override
    public void deleteCltDetailOrder(String orderId)
    {
        cltOrderDetailMapper.updateIsDeletedByUser(orderId);
    }

    @Override
    public void actualDeliveryTime(String orderId, String realTime)
    {
        cltOrderDetailMapper.changeCltOrderRealtime(orderId, realTime);
    }

}
