package buptworker.mapper;

import buptworker.entity.CltOrderDDetailInfo;
import buptworker.entity.CltOrderDetailItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CltOrderDetailMapper {

    @Select("SELECT m.merchant_name AS mchname,  " +
            "       o.merchant_id AS mchId,  " +
            "       a.address_line AS address,  " +
            "       o.notes AS note,  " +
            "       o.packaging_fee AS packingCharge,  " +
            "       o.delivery_fee AS deliveryCharge,  " +
            "       o.order_date AS orderdate,  " +
            "       o.estimated_delivery_time AS predictdate,  " +
            "       o.actual_delivery_time AS realdate,  " +
            "       o.payment_method AS payway,  " +
            "       o.order_status AS state,  " +
            "       (o.total_amount - o.packaging_fee - o.delivery_fee) AS pdtmoney,  " +
            "       o.total_amount AS totalmoney " +
            "FROM Orders o " +
            "JOIN Merchant m ON o.merchant_id = m.merchant_id " +
            "JOIN Address a ON o.address_id = a.address_id " +
            "WHERE o.order_id = #{orderId} " +
            "AND o.is_deleted_by_user = FALSE; ")
    public List<CltOrderDetailItem> getcltOrderDetailItemByOrderId(@Param("orderId") String orderId);

    @Select("SELECT p.imageUrl AS picture,  " +
            "       p.product_name AS name,  " +
            "       p.price AS price,  " +
            "       oi.quantity AS num " +
            "FROM Order_Item oi " +
            "JOIN Product p ON oi.product_id = p.product_id " +
            "WHERE oi.order_id = #{orderId}; ")
    public List<CltOrderDDetailInfo> listCltOrderDetailInfoByOrderId(@Param("orderId") String orderId);


    @Update("UPDATE Orders " +
            "SET order_status = #{newStatus} " +
            "WHERE order_id = #{orderId}; ")
    public void updateOrderDetailStatus(@Param("orderId") String orderId, @Param("newStatus") String newStatus);

    @Update("UPDATE Orders " +
            "SET is_deleted_by_user = TRUE " +
            "WHERE order_id = #{orderId}; ")
    public void updateIsDeletedByUser(@Param("orderId") String orderId);

}
