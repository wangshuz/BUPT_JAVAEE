package buptworker.controller;


import buptworker.entity.Result;
import buptworker.service.MchOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")  // 允许 http://localhost:8080 的跨域请求
public class MchOrderController {

    @Autowired
    private MchOrderService mchOrderService;

    @RequestMapping("/getOrders")
    public Result getOrders(String mchId) {
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
        return Result.success("删除成功");
    }
}
