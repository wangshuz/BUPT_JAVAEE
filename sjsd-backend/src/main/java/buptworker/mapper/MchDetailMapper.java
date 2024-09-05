package buptworker.mapper;

import buptworker.entity.Merchant;
import buptworker.entity.MchType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MchDetailMapper {

    @Select("SELECT m.merchant_id, m.merchant_avatar, m.merchant_name, m.type_id, " +
            "t.type_name, m.delivery_fee, m.packaging_fee_per_item, m.minimum_order_amount, " +
            "m.opening_hours, m.phone_number, m.merchant_address, m.merchant_description, m.is_open " +
            "FROM merchant m " +
            "JOIN merchant_type t ON m.type_id = t.type_id " +
            "WHERE m.merchant_id = #{merchantID}")
    @Results({
            @Result(property = "merchantID", column = "merchant_id"),
            @Result(property = "avatarURL", column = "merchant_avatar"),
            @Result(property = "merchantName", column = "merchant_name"),
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "typeName", column = "type_name"), // 映射 typeName
            @Result(property = "deliveryFee", column = "delivery_fee"),
            @Result(property = "packagingFeePerItem", column = "packaging_fee_per_item"),
            @Result(property = "minimumOrderAmount", column = "minimum_order_amount"),
            @Result(property = "openingHours", column = "opening_hours"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "merchantAddress", column = "merchant_address"),
            @Result(property = "merchantDescription", column = "merchant_description"),
            @Result(property = "isOpen", column = "is_open")
    })
    Merchant getMerchantById(int merchantID);


    @Update("UPDATE merchant SET merchant_name = #{merchantName}, merchant_address = #{merchantAddress}, " +
            "phone_number = #{phoneNumber}, opening_hours = #{openingHours}, " +
            "merchant_description = #{merchantDescription}, is_open = #{isOpen}, delivery_fee = #{deliveryFee}, " +
            "minimum_order_amount = #{minimumOrderAmount}, packaging_fee_per_item = #{packagingFeePerItem} " +
            "WHERE merchant_id = #{merchantID}")
    int updateMerchant(Merchant merchant);

    @Update("UPDATE merchant SET merchant_avatar = #{avatarURL} WHERE merchant_id = #{merchantID}")
    int updateMerchantAvatar(int merchantID, String avatarURL);

    @Select("SELECT type_name FROM merchant_type")
    List<String> getTypeName();
}
