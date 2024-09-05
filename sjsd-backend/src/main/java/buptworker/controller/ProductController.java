package buptworker.controller;

import buptworker.entity.Result;
import buptworker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 15:35]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 15:35]
 * @updateRemark : [说明本次修改内容]
 */

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class ProductController {
    private Integer merchantId = 1; // 测试数据
    private Integer cltId = 1;  // 测试数据
    @Autowired
    private ProductService productService;
    @RequestMapping("/productClt")
    public Result productClt(){
        return Result.success(productService.listProductClt(merchantId));
    }

    @RequestMapping("/productMch")
    public Result productMch(){
        return Result.success(productService.listProductMch(merchantId));
    }

    @RequestMapping("/mchInfo")
    public Result mchInfo(){
        return Result.success(productService.getMerchant(merchantId));
    }

    @RequestMapping("/proType")
    public Result proType() {
        return Result.success(productService.listProType(merchantId));
    }

    @RequestMapping("/cltAddress")
    public Result cltAddress(){
        return Result.success(productService.getCltAddress(cltId));
    }

}
