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
public class ProductController {

    @Autowired
    private ProductService productService;
    @RequestMapping("/productClt")
    public Result productClt(int merchantId){
        return Result.success(productService.listProductClt(merchantId));
    }

    @RequestMapping("/productClt1")
    public int productMch(int merchantId){
        return merchantId;
    }

    @RequestMapping("/getMerchant")
    public Result mchInfo(int merchantId){
        return Result.success(productService.getMerchant(merchantId));
    }
}
