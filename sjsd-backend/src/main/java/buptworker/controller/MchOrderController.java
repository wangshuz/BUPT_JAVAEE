package buptworker.controller;


import buptworker.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MchOrderController {

    @RequestMapping("/getOrders")
    public Result getOrder(String mchId) {
        return Result.success(/*特定商家的订单信息列表*/);
    }

    @RequestMapping("/updateOrderStatus")
    public Result updateOrderStatus(String orderId, String status) {
        return Result.success();
    }

    @RequestMapping("/deleteOrder")
    public Result deleteOrder(String orderId) {
        return Result.success();
    }
}
