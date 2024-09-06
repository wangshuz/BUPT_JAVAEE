package buptworker.controller;

import buptworker.entity.Result;
import buptworker.service.CltOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import buptworker.util.SessionUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class CltOrderController {
    @Autowired
    private CltOrderService cltOrderService;


    private SessionUtil cookie;
    private Integer defaultMerchantID = 5;  // 修改为cookie！！！

    @RequestMapping("/getCltOrders")
    public Result getCltOrders(HttpServletRequest request) {
        defaultMerchantID = cookie.getUserID(request).intValue();
        return Result.success(cltOrderService.getCltOrders(defaultMerchantID.toString()));
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
