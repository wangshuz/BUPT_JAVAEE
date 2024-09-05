package buptworker.mapper;

import buptworker.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT p.product_id AS id, p.product_name AS name, p.price AS price, p.description AS decription, p.available AS status, p.imageUrl AS image, " +
            "pc.category_id, pc.category_name " +
            "FROM product p " +
            "JOIN product_category pc ON p.category_id = pc.category_id " +
            "WHERE p.merchant_id = #{merchantId} " +
            "AND p.is_deleted = 0 " +
            "AND pc.is_deleted = 0 ")
    public List<Product> listProduct(@Param("merchantId") int merchantId);

    @Select("SELECT m.merchant_id AS merchantID, " +
            "       m.merchant_name AS merchantName, " +
            "       m.merchant_avatar AS avatarURL, " +
            "       m.merchant_address AS merchantAddress, " +
            "       m.phone_number AS phonenumber, " +
            "       m.opening_hours AS openingHours, " +
            "       m.merchant_description AS merchantDescription, " +
            "       m.delivery_fee AS deliveryFee, " +
            "       m.minimum_order_amount AS minimumOrderAmount, " +
            "       m.packaging_fee_per_item AS packagingFeePerItem, " +
            "       mt.type_name AS typeName " +
            "FROM merchant m " +
            "JOIN merchant_type mt ON m.type_id = mt.type_id " +
            "WHERE m.merchant_id = #{merchantId}")
    public Merchant getMerchant(@Param("merchantId") int merchantId);

    @Select("SELECT category_id AS typeId, category_name AS typeName "+
            "FROM product_category p "+
            "WHERE p.merchant_id=#{merchantId}")
    public List<ProType> listProType(@Param("merchantId") int merchantId);

    @Select("SELECT address_id AS id, recipient_name AS name, recipient_phone AS phone, Address_line AS address, is_default " +
            "FROM Address a " +
            "WHERE a.user_id = #{CltId}")
    public List<Address> listAddress(@Param("CltId") int cltId);



}
