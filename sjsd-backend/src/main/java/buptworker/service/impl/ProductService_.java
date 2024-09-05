package buptworker.service.impl;

import buptworker.entity.*;
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
            if(item.getStatus()==Boolean.TRUE) {
                ProductClt t = new ProductClt(item);
                ret.add(t);
            }
        });
        return ret;
    }

    @Override
    public List<ProductMch> listProductMch(int merchantId) {
        List<ProductMch> ret = new ArrayList<>();
        List<Product> products = productMapper.listProduct(merchantId);
        products.stream().forEach(item->{
            ProductMch t = new ProductMch(item);
            ret.add(t);
        });
        return ret;
    }

    @Override
    public Merchant getMerchant(int merchantId) {
        return productMapper.getMerchant(merchantId);
    }

    @Override
    public List<ProType> listProType(int merchantId) {
        return productMapper.listProType(merchantId);
    }

    @Override
    public CltAddress getCltAddress(int cltId) {
        List<Address> addressList = productMapper.listAddress(cltId);
        Address curAddress = new Address();
        for(Address item : addressList){
            if(item.getIs_default()==Boolean.TRUE) {
                curAddress = item;
                break;
            }
        }
        return new CltAddress(curAddress, addressList);
    }




}
