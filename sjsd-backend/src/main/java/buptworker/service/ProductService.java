package buptworker.service;

import buptworker.entity.Merchant;
import buptworker.entity.ProductClt;
import buptworker.entity.ProductMch;

import java.util.List;

public interface ProductService {
    public List<ProductClt> listProductClt(int merchantId);

    public List<ProductMch> listProductMch(int merchantId);

    public Merchant getMerchant(int merchant);
//    public List<Merchant> list
}
