package buptworker.mapper;

import buptworker.entity.MchOrderDetail;
import buptworker.entity.MchOrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MchOrderMapper {
    @Select("SELECT " +
            "   o.order_id AS orderId," +
            "   u.username AS username," +
            "   a.recipient_phone AS phonenumber," +
            "   a.address_line AS location," +
            "   o.order_date AS ordertime," +
            "   o.estimated_delivery_time AS perdicttime," +
            "   o.actual_delivery_time AS realtime," +
            "   o.notes AS note," +
            "   o.packaging_fee AS packingCharge," +
            "   o.delivery_fee AS deliveryCharge," +
            "   (o.total_amount - o.packaging_fee - o.delivery_fee) AS totalmchpricenote," +
            "   o.payment_method AS payway," +
            "   o.total_amount AS money," +
            "   o.order_status AS orderstate " +
            "FROM " +
            "   Orders o " +
            "JOIN " +
            "   User u ON o.user_id = u.user_id " +
            "JOIN " +
            "   Address a ON o.address_id = a.address_id " +
            "WHERE " +
            "   o.merchant_id = #{{mchID}} " +
            "   AND o.is_deleted_by_merchant = FALSE; "
    )
    public List<MchOrderDetail> listMchOrderDetail(@Param("mchID") String mchID);

    @Select("SELECT " +
            "    p.product_name AS name, " +
            "    p.price AS price, " +
            "    oi.quantity AS num " +
            "FROM  " +
            "    Order_Item oi " +
            "JOIN  " +
            "    Product p ON oi.product_id = p.product_id " +
            "WHERE  " +
            "    oi.order_id = #{orderId}; ")
    public List<MchOrderItem> listMchOrderItem(@Param("orderId") String orderId);

    @Update("UPDATE Orders " +
            "SET order_status = #{orderStatus} " +
            "WHERE order_id = #{orderId}; ")
    public void updateOrderStatus(@Param("orderId") String orderId, @Param("orderStatus") String orderStatus);

    @Update("UPDATE Orders " +
            "SET is_deleted_by_merchant = FALSE " +
            "WHERE order_id = ?; ")
    public void updateIsDeleted(@Param("orderId") String orderId);
}
