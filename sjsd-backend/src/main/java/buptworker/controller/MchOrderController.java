package buptworker.controller;


import buptworker.entity.Result;
import buptworker.service.MchOrderService;
import buptworker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MchOrderController {

    @Autowired
    private MchOrderService mchOrderService;

    @RequestMapping("/getOrders")
    public Result getOrder(String mchId) {
        return Result.success(mchOrderService.getMchOrders(mchId));
    }

    @RequestMapping("/updateOrderStatus")
    public Result updateOrderStatus(String orderId, String status) {
        mchOrderService.updateOrderStatus(orderId, status);
        return Result.success();
    }

    @RequestMapping("/deleteOrder")
    public Result deleteOrder(String orderId) {
        mchOrderService.deleteMchOrder(orderId);
        return Result.success();
    }
}
