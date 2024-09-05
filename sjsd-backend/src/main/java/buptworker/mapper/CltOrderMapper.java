package buptworker.mapper;

import buptworker.entity.CltOrderInfo;
import buptworker.entity.CltOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CltOrderMapper {
    @Select("SELECT o.order_id AS orderId, o.merchant_id AS mchId, m.merchant_name AS mchname, o.order_date As data, o.order_status AS state, o.total_amount AS money " +
            "FROM Orders o " +
            "JOIN Merchant m ON o.merchant_id = m.merchant_id " +
            "WHERE o.user_id = #{userId} " +
            "AND o.is_deleted_by_user = FALSE;")
    public List<CltOrderItem> getCltOrderItems(@Param("userId") String userId);

    @Select("SELECT p.imageUrl AS picture, p.product_name AS name, p.price AS price, oi.quantity AS num " +
            "FROM Order_Item oi " +
            "JOIN Product p ON oi.product_id = p.product_id " +
            "WHERE oi.order_id = #{orderId}; ")
    public List<CltOrderInfo> getCltOrderInfos(@Param("orderId") String orderId);

    @Update("UPDATE Orders " +
            "SET order_status = #{newStatus} " +
            "WHERE order_id = #{orderId}; ")
    public void updateOrderStatus(@Param("orderId") String orderId, @Param("newStatus") String newStatus);

    @Update("UPDATE Orders " +
            "SET is_deleted_by_user = TRUE " +
            "WHERE order_id = #{orderId}; ")
    public void deleteCltMchOrder(@Param("orderId") String orderId);

}
