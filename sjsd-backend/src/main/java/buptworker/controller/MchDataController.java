package buptworker.controller;

import buptworker.entity.Result;
import buptworker.service.MchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 21:08]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 21:08]
 * @updateRemark : [说明本次修改内容]
 */

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class MchDataController {
    @Autowired
    private MchDataService mchDataService;
    @RequestMapping("/salesData")
    public Result listSalesData(int merchantId){
        return Result.success(mchDataService.listSalesData(merchantId));
    }
    @RequestMapping("/curData")
    public Result curData(int merchantId){
        return Result.success(mchDataService.getCurData(merchantId));
    }
    @RequestMapping("/merchantInfo")
    public Result merchantInfo(int merchantId){
        return Result.success(mchDataService.getMerchantInfo(merchantId));
    }
    @RequestMapping("/prodStats")
    public Result prodStats(int merchantId){
        return Result.success(mchDataService.getProdStats(merchantId));
    }
    @RequestMapping("monthlyOrderStats")
    public Result getMonthlyOrderStats(int merchantId){
        return Result.success(mchDataService.getMonthlyOrderStats(merchantId));
    }

}
