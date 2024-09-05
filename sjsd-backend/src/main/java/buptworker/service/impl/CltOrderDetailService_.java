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
    public List<CltOrderDetail> getCltOrderDetail(String orderId)
    {
        List<CltOrderDetail> cltOrdersDetails = new ArrayList<CltOrderDetail>();
        List<CltOrderDetailItem> cltOrderDetailItems = cltOrderDetailMapper.getcltOrderDetailItemByOrderId(orderId);
        for (CltOrderDetailItem cltOrderDetailItem : cltOrderDetailItems) {
            switch (cltOrderDetailItem.getState()) {
                case "1", "2", "3" -> cltOrderDetailItem.setState("1");
                case "4" -> cltOrderDetailItem.setState("2");
                case "5" -> cltOrderDetailItem.setState("3");
            }
            List<CltOrderDDetailInfo> cltOrderDetailInfos = cltOrderDetailMapper.listCltOrderDetailInfoByOrderId(orderId);
            cltOrdersDetails.add(new CltOrderDetail(cltOrderDetailItem, cltOrderDetailInfos));
        }
        return cltOrdersDetails;
    }

    public void updateCltOrderDetailStatus(String orderId, String status)
    {
        cltOrderDetailMapper.updateOrderDetailStatus(orderId, status);
    }

    public void deleteCltDetailOrder(String orderId)
    {
        cltOrderDetailMapper.updateIsDeletedByUser(orderId);
    }


}
