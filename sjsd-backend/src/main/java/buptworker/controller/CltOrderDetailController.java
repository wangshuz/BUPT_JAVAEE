package buptworker.controller;

import buptworker.entity.Result;
import buptworker.service.CltOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
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
}
