package buptworker.controller;

import buptworker.entity.Result;
import buptworker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 15:35]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 15:35]
 * @updateRemark : [说明本次修改内容]
 */

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;
    @RequestMapping("/productClt")
    public Result productClt(int merchantId){
        return Result.success(productService.listProductClt(merchantId));
    }

    @RequestMapping("/productMch")
    public Result productMch(int merchantId){
        return Result.success(productService.listProductMch(merchantId));
    }

    @RequestMapping("/mchInfo")
    public Result mchInfo(int merchantId){
        return Result.success(productService.getMerchant(merchantId));
    }

    @RequestMapping("/proType")
    public Result proType(int merchantId) {
        return Result.success(productService.listProType(merchantId));
    }

    @RequestMapping("/cltAddress")
    public Result cltAddress(int cltId){
        return Result.success(productService.getCltAddress(cltId));
    }

}
