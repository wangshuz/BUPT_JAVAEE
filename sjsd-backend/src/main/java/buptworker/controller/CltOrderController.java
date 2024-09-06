package buptworker.controller;

import buptworker.entity.Result;
import buptworker.service.CltOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")  // 允许 http://localhost:8080 的跨域请求
public class CltOrderController {
    @Autowired
    private CltOrderService cltOrderService;

    @RequestMapping("/getCltOrders")
    public Result getCltOrders() {
        return Result.success(cltOrderService.getCltOrders("1"));
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
    public Result changeCltOrderRealtime(String orderId, String time) {
        cltOrderService.actualDeliveryTime(orderId, time);
        return Result.success();
    }
}
