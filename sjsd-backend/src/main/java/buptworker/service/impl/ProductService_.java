package buptworker.service.impl;

import buptworker.entity.*;
import buptworker.mapper.ProductMapper;
import buptworker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : [Xieyx]
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

    @Override
    public int addCategory(String categoryName) {
        // 先检查分类名称是否已存在
        int existingCount = productMapper.countByCategoryName(categoryName);
        if (existingCount > 0) {
            return 0;  // 分类名称已存在
        }

        // 插入新的分类
        int result = productMapper.insertCategory(categoryName);
        return result > 0 ? 1 : 0;  // 如果插入成功返回 1，否则返回 0
    }

    @Override
    public int deleteProduct(int productId) {
        // 调用Mapper层方法执行软删除
        int result = productMapper.deleteProduct(productId);
        return result > 0 ? 1 : 0;  // 如果删除成功返回 1，否则返回 0
    }

    @Override
    public int updateProductStatus(int productId, boolean status) {
        // 调用Mapper层执行更新操作
        int result = productMapper.updateProductStatus(productId, status);
        return result > 0 ? 1 : 0;  // 如果更新成功返回 1，否则返回 0
    }

    @Override
    public int addProduct(Product product, int merchantId) {
        // 调用Mapper层插入商品
        return productMapper.insertProduct(product, merchantId);
    }

    @Override
    public int updateProduct(Product product, int merchantId) {
        productMapper.deleteProduct(product.getId());
        int ret = productMapper.insertProduct(product, merchantId);
        return ret;
    }

    @Override
    public void createOrder(Order order, List<OrderItem> itemList) {
        int order_id = productMapper.insertOrder(order);
        itemList.stream().forEach(it->{
            it.setOrder_id(order_id);
            productMapper.insertOrderItem(it);
        });
    }
}
