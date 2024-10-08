package buptworker.service;

import buptworker.entity.*;

import java.util.List;

public interface ProductService {
    public List<ProductClt> listProductClt(int merchantId);

    public List<ProductMch> listProductMch(int merchantId);

    public Merchant getMerchant(int merchantId);

    //    public List<Merchant> list
    public List<ProType> listProType(int merchantId);

    public CltAddress getCltAddress(int cltId);

    public int addCategory(String categoryName, int merchantId);

    public int deleteProduct(int productId);

    public int updateProductStatus(int productId, boolean status);

    public int addProduct(Product product, int merchantId);

    public int updateProduct(Product product, int merchantId);

    public void createOrder(Order order, List<OrderItem> itemList);
}
