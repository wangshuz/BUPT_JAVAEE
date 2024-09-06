package buptworker.controller;

import buptworker.entity.Result;
import buptworker.service.MchDataService;
import buptworker.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 21:08]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 21:08]
 * @updateRemark : [说明本次修改内容]
 */

@RestController
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RequestMapping("/api")
public class MchDataController {
    private SessionUtil cookie;
    private Integer merchantId = 1;
    @Autowired
    private MchDataService mchDataService;

    @RequestMapping("/salesData")
    public Result listSalesData(HttpServletRequest request){
        int merchantId = cookie.getUserID(request).intValue();
        return Result.success(mchDataService.listSalesData(merchantId));
    }
    @RequestMapping("/curData")
    public Result curData(HttpServletRequest request){
        int merchantId = cookie.getUserID(request).intValue();
        return Result.success(mchDataService.getCurData(merchantId));
    }
    @RequestMapping("/merchantInfo")
    public Result merchantInfo(HttpServletRequest request){
        int merchantId = cookie.getUserID(request).intValue();
        return Result.success(mchDataService.getMerchantInfo(merchantId));
    }
    @RequestMapping("/prodStats")
    public Result prodStats(HttpServletRequest request){
        int merchantId = cookie.getUserID(request).intValue();
        return Result.success(mchDataService.getProdStats(merchantId));
    }
    @RequestMapping("monthlyOrderStats")
    public Result getMonthlyOrderStats(HttpServletRequest request){
        int merchantId = cookie.getUserID(request).intValue();
        return Result.success(mchDataService.getMonthlyOrderStats(merchantId));
    }

}
