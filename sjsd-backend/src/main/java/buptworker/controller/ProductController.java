package buptworker.controller;

import buptworker.entity.*;
import buptworker.service.ProductService;
import buptworker.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RequestMapping("/api")
public class ProductController {
    private SessionUtil cookie;
    @Autowired
    private ProductService productService;
    @RequestMapping("/productClt")
    public Result productClt(HttpServletRequest request){
        int merchantId = cookie.getUserID(request).intValue();
        return Result.success(productService.listProductClt(merchantId));
    }

    @RequestMapping("/productMch")
    public Result productMch(HttpServletRequest request){
        int merchantId = cookie.getUserID(request).intValue();
        return Result.success(productService.listProductMch(merchantId));
    }

    @RequestMapping("/mchInfo")
    public Result mchInfo(int merchantId){
        return Result.success(productService.getMerchant(merchantId));
    }

    @RequestMapping("/proType")
    public Result proType(HttpServletRequest request) {
        int merchantId = cookie.getUserID(request).intValue();
        return Result.success(productService.listProType(merchantId));
    }

    @RequestMapping("/cltAddress")
    public Result cltAddress(HttpServletRequest request){
        int cltId = cookie.getUserID(request).intValue();
        return Result.success(productService.getCltAddress(cltId));
    }


    @RequestMapping("/category/add")
    public Result addCategory(String categoryName){
        int t = productService.addCategory(categoryName);
        if(t==1){
            return Result.success();
        }else{
            return Result.error("类型已存在");
        }
    }

    @RequestMapping("/product/delete")
    public Result deleteProduct(int productId){
        int t = productService.deleteProduct(productId);
        if(t==1){
            return Result.success();
        }else{
            return Result.error("删除失败");
        }
    }

    @RequestMapping("/product/updateStatus")
    public Result updateProductStatus(int productId, boolean status){
        int t = productService.updateProductStatus(productId,status);
        if(t==1){
            return Result.success();
        }else{
            return Result.error("状态更新失败");
        }
    }

    @RequestMapping("/product/add")
    public Result addProduct(@RequestBody Product product,HttpServletRequest request) {
        int merchantId = cookie.getUserID(request).intValue();
        int result = productService.addProduct(product, merchantId);
        if (result > 0) {
            return Result.success();
        } else {
            return Result.error("新增商品失败");
        }
    }

    @RequestMapping("/product/update")
    public Result updataProduct(@RequestBody Product product,HttpServletRequest request){
        int merchantId = cookie.getUserID(request).intValue();
        int result = productService.updateProduct(product,merchantId);
        if (result > 0) {
            return Result.success();
        } else {
            return Result.error("新增商品失败");
        }
    }

    @RequestMapping("/order/submit")
    public Result insertOrdre(@RequestBody OrderPackage orderPackage,HttpServletRequest request){
        int cltId = cookie.getUserID(request).intValue();
        Order order = orderPackage.getOrder();
        List<OrderItem> itemList = orderPackage.getItemList();
        order.setUserId(cltId);
        productService.createOrder(order,itemList);
        return Result.success();
    }


}
