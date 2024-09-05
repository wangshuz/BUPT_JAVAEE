package buptworker.controller;

import buptworker.entity.Result;
import buptworker.service.CltOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CltOrderController {
    @Autowired
    private CltOrderService cltOrderService;

    @RequestMapping("/getCltOrders")
    public Result getCltOrders(String userId) {
        return Result.success(cltOrderService.getCltOrders(userId));
    }

    @RequestMapping("/updateCltOrderStatus")
    public Result updateCltOrderStatus(String orderId, String status) {
        cltOrderService.updateCltOrderStatus(orderId, status);
        return Result.success();
    }

    @RequestMapping("/deleteCltOrder")
    public Result deleteCltOrder(String orderId) {
        cltOrderService.deleteCltMchOrder(orderId);
        return Result.success();
    }

    @RequestMapping("/changeCltOrderRealtime")
    public Result changeCltOrderRealtime(String orderId, String realtime) {
        cltOrderService.actualDeliveryTime(orderId, realtime);
        return Result.success();
    }
}
