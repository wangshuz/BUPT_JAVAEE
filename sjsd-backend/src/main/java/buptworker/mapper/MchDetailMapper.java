package buptworker.mapper;

import buptworker.entity.Merchant;
import buptworker.entity.MchType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MchDetailMapper {

    @Select("SELECT * FROM merchant WHERE merchant_id = #{merchantID}")
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
