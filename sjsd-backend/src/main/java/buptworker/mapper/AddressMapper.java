package buptworker.mapper;

import buptworker.entity.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper {

    // 查询用户的所有地址（未删除的地址）
    @Select("SELECT a.address_id as id ,a.recipient_name as name ,a.recipient_phone as phone ,a.address_line as address ,a.is_default as is_default FROM address a " +
            "WHERE user_id = #{id} " +
            "AND is_deleted = 0")
    List<Address> findAddressesByUserId(@Param("id") Integer id);

    // 插入新地址
    @Insert("INSERT INTO address (user_id, recipient_name, recipient_phone, address_line, is_default, is_deleted) " +
            "VALUES (#{userid}, #{name}, #{phone}, #{address}, #{is_default}, 0)")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 获取自动生成的地址ID
    void insertAddress(Address address, @Param("userid") Integer userid);
    // 逻辑删除当前地址（将 is_deleted 设置为 1）
    @Update("UPDATE address SET is_deleted = 1 " +
            "WHERE address_id = #{id}")
    void markAddressAsDeleted(@Param("id") Integer id);

    // 删除地址（逻辑删除，将 is_deleted 设置为 1）
    @Update("UPDATE address SET is_deleted = 1 " +
            "WHERE address_id = #{id}")
    void deleteAddressById(@Param("id") Integer id);

    // 清除用户的所有默认地址
    @Update("UPDATE address SET is_default = 0 " +
            "WHERE user_id = #{userId} " +
            "AND is_deleted = 0")
    void clearDefaultAddresses(@Param("userId") int userId);
//    // 更新地址信息
//    @Update("UPDATE address SET " +
//            "recipient_name = #{recipientName}, " +
//            "recipient_phone = #{recipientPhone}, " +
//            "address_line = #{addressLine}, " +
//            "is_default = #{isDefault} " +
//            "WHERE address_id = #{id}")
//    void updateAddress(Address address);
//
//    // 删除地址（逻辑删除，将 is_deleted 设置为 1）
//    @Update("UPDATE address SET is_deleted = 1 " +
//            "WHERE address_id = #{addressId}")
//    void deleteAddressById(@Param("addressId") int addressId);
//
//    // 清除用户的所有默认地址
//    @Update("UPDATE address SET is_default = 0 " +
//            "WHERE user_id = #{userId} " +
//            "AND is_deleted = 0")
//    void clearDefaultAddresses(@Param("userId") int userId);

}



//<mapper namespace="com.example.mapper.AddressMapper">
//
//    <!-- 查询用户的所有地址 -->
//    <select id="findAddressesByUserId" parameterType="int" resultType="com.example.entity.Address">
//SELECT * FROM address
//WHERE user_id = #{userId}
//AND is_deleted = 0
//        </select>
//
//    <!-- 插入新地址 -->
//    <insert id="insertAddress" parameterType="com.example.entity.Address">
//INSERT INTO address (user_id, recipient_name, recipient_phone, address_line, is_default, is_deleted)
//VALUES (#{userId}, #{recipientName}, #{recipientPhone}, #{addressLine}, #{isDefault}, 0)
//    </insert>
//
//    <!-- 更新地址信息 -->
//    <update id="updateAddress" parameterType="com.example.entity.Address">
//UPDATE address
//SET recipient_name = #{recipientName},
//recipient_phone = #{recipientPhone},
//address_line = #{addressLine},
//is_default = #{isDefault}
//WHERE address_id = #{addressId}
//    </update>
//
//    <!-- 删除地址（实际是设置 is_deleted 为 1） -->
//    <update id="deleteAddressById" parameterType="int">
//UPDATE address
//SET is_deleted = 1
//WHERE address_id = #{addressId}
//    </update>
//
//    <!-- 清除用户的所有默认地址 -->
//    <update id="clearDefaultAddresses" parameterType="int">
//UPDATE address
//SET is_default = 0
//WHERE user_id = #{userId}
//AND is_deleted = 0
//        </update>
//
//</mapper>
