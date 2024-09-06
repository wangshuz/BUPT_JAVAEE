package buptworker.controller;


import buptworker.entity.Result;
import buptworker.service.MchOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import buptworker.util.SessionUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class MchOrderController {

    @Autowired
    private MchOrderService mchOrderService;

    private Integer defaultMerchantID = 5;  // 修改为cookie！！！
    private SessionUtil cookie;

    @RequestMapping("/getOrders")
    public Result getOrders(HttpServletRequest request) {
        defaultMerchantID = cookie.getUserID(request).intValue();
        return Result.success(mchOrderService.getMchOrders(defaultMerchantID.toString()));
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
