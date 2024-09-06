package buptworker.controller;

import buptworker.entity.Result;
import buptworker.service.CltOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")  // 允许 http://localhost:8080 的跨域请求
public class CltOrderDetailController {

    @Autowired
    private CltOrderDetailService cltOrderDetailService;

    @RequestMapping("/getCltOrderDetail")
    public Result getCltOrderDetail(String orderId) {
        return Result.success(cltOrderDetailService.getCltOrderDetail(orderId));
    }

    @RequestMapping("/updateCltOrderDetailStatus")
    public Result updateCltOrderDetailStatus(String orderId, String status) {
        cltOrderDetailService.updateCltOrderDetailStatus(orderId, status);
        return Result.success();
    }

    @RequestMapping("/deleteCltOrderDetail")
    public Result deleteCltOrderDetail(String orderId) {
        cltOrderDetailService.deleteCltDetailOrder(orderId);
        return Result.success();
    }

    @RequestMapping("/changeCltOrderRealtime")
    public Result changeCltOrderRealtime(String orderId, String realtime) {
        cltOrderDetailService.actualDeliveryTime(orderId, realtime);
        return Result.success();
    }
}
