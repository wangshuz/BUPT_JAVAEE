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
}
