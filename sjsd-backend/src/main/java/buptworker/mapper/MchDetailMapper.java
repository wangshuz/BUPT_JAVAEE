package buptworker.mapper;

import buptworker.entity.Merchant;
import buptworker.entity.MchType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MchDetailMapper {

    @Select("SELECT * FROM merchant WHERE merchant_id = #{merchantID}")
    @Results({
            @Result(property = "merchantID", column = "merchant_id"), // 数据库字段 merchant_id 对应类中的 merchantID
            @Result(property = "avatarURL", column = "merchant_avatar"), // 数据库字段 avatar_url 对应类中的 avatarURL
            @Result(property = "merchantName", column = "merchant_name"), // 数据库字段 merchant_name 对应类中的 merchantName
            @Result(property = "typeName", column = "type_id"), // 数据库字段 type_name 对应类中的 typeName
            @Result(property = "deliveryFee", column = "delivery_fee"), // 数据库字段 delivery_fee 对应类中的 deliveryFee
            @Result(property = "packagingFeePerItem", column = "packaging_fee_per_item"), // 数据库字段 packaging_fee_per_item 对应类中的 packagingFeePerItem
            @Result(property = "minimumOrderAmount", column = "minimum_order_amount"), // 数据库字段 minimum_order_amount 对应类中的 minimumOrderAmount
            @Result(property = "openingHours", column = "opening_hours"), // 数据库字段 opening_hours 对应类中的 openingHours
            @Result(property = "phoneNumber", column = "phone_number"), // 数据库字段 phone_number 对应类中的 phoneNumber
            @Result(property = "merchantAddress", column = "merchant_address"), // 数据库字段 merchant_address 对应类中的 merchantAddress
            @Result(property = "merchantDescription", column = "merchant_description"), // 数据库字段 merchant_description 对应类中的 merchantDescription
            @Result(property = "isOpen", column = "is_open") // 数据库字段 is_open 对应类中的 isOpen
    })
    Merchant getMerchantById(int merchantID);

    @Update("UPDATE merchant SET merchant_name = #{merchantName}, merchant_address = #{merchantAddress}, " +
            "phone_number = #{phoneNumber}, opening_hours = #{openingHours}, business_type = #{typeName}, " +
            "merchant_description = #{merchantDescription}, is_open = #{isOpen}, delivery_fee = #{deliveryFee}, " +
            "minimum_order_amount = #{minimumOrderAmount}, packaging_fee_per_item = #{packagingFeePerItem} " +
            "WHERE merchant_id = #{merchantID}")
    int updateMerchant(Merchant merchant);

    @Update("UPDATE merchant SET avatar_url = #{avatarURL} WHERE merchant_id = #{merchantID}")
    int updateMerchantAvatar(int merchantID, String avatarURL);

    @Select("SELECT type_name FROM merchant_type")
    List<String> getTypeName();
}
