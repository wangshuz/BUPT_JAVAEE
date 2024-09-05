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
    @Select("SELECT o.order_id, m.merchant_name, o.order_date, o.order_status, o.total_amount " +
            "FROM Orders o " +
            "JOIN Merchant m ON o.merchant_id = m.merchant_id " +
            "WHERE o.user_id = #{userId} " +
            "AND o.is_deleted_by_user = FALSE;")
    public List<CltOrderItem> getCltOrderItems(@Param("userId") String userId);

    @Select("SELECT p.imageUrl, p.product_name, p.price, oi.quantity " +
            "FROM Order_Item oi " +
            "JOIN Product p ON oi.product_id = p.product_id " +
            "WHERE oi.order_id = #{orderId}; ")
    public List<CltOrderInfo> getCltOrderInfos(@Param("orderId") String orderId);

    @Update("UPDATE Orders " +
            "SET order_status = #{newStatus} " +
            "WHERE order_id = #{orderId}; ")
    public void updateOrderStatus(@Param("orderId") String orderId, @Param("newStatus") String newStatus);

    @Update("UPDATE Orders " +
            "SET is_deleted_by_user = FALSE " +
            "WHERE order_id = #{orderId}; ")
    public void deleteCltMchOrder(@Param("orderId") String orderId);

}
