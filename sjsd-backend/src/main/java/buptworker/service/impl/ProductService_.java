package buptworker.service.impl;

import buptworker.entity.Merchant;
import buptworker.entity.Product;
import buptworker.entity.ProductClt;
import buptworker.entity.ProductMch;
import buptworker.mapper.ProductMapper;
import buptworker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 15:22]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 15:22]
 * @updateRemark : [说明本次修改内容]
 */

@Component
public class ProductService_ implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<ProductClt> listProductClt(int merchantId) {
        List<ProductClt> ret = new ArrayList<ProductClt>();
        List<Product> products = productMapper.listProduct(merchantId);
        products.stream().forEach(item->{
            ProductClt t = new ProductClt(item);
            ret.add(t);
        });
        return ret;
    }

    @Override
    public List<ProductMch> listProductMch(int merchantId) {
        return null;
    }

    @Override
    public Merchant getMerchant(int merchantId) {
        return productMapper.getMerchant(merchantId);
    }


}
