package buptworker.mapper;

import buptworker.entity.MerchantInfo;
import buptworker.entity.OrderStats;
import buptworker.entity.ProductStats;
import buptworker.entity.SalesData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MchDataMapper {

    // 查询近30天每天的销量
    @Select("SELECT DATE(o.order_date) AS orderDate, " +
            "SUM(oi.quantity) AS totalSalesVolume " +
            "FROM Orders o " +
            "JOIN Order_Item oi ON o.order_id = oi.order_id " +
            "WHERE o.merchant_id = #{merchantId} " +
            "AND o.order_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 30 DAY) AND CURDATE() " +
            "AND o.order_status = 'completed' " +
            "GROUP BY DATE(o.order_date) " +
            "ORDER BY DATE(o.order_date) DESC")
    List<SalesData> listSalesVolume(@Param("merchantId") int merchantId);

    // 查询近30天每天的销售额
    @Select("SELECT DATE(order_date) AS orderDate, " +
            "SUM(total_amount) AS totalSalesAmount " +
            "FROM Orders " +
            "WHERE merchant_id = #{merchantId} " +
            "AND order_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 30 DAY) AND CURDATE() " +
            "AND order_status = 'completed' " +
            "GROUP BY DATE(order_date) " +
            "ORDER BY DATE(order_date) DESC")
    List<SalesData> listSalesAmount(@Param("merchantId") int merchantId);

    // 查询近30天每天的销量、销售额、总订单数和顾客数
    @Select("SELECT DATE(o.order_date) AS orderDate, " +
            "COUNT(DISTINCT o.order_id) AS totalOrderCount, " +  // 计算总订单数
            "COUNT(DISTINCT o.user_id) AS totalCustomerCount, " +  // 计算顾客数
            "SUM(oi.quantity) AS totalSalesVolume, " +           // 计算总销量
            "SUM(o.total_amount) AS totalSalesAmount " +         // 计算总销售额
            "FROM Orders o " +
            "JOIN (SELECT order_id, SUM(quantity) AS quantity " +
            "      FROM Order_Item " +
            "      GROUP BY order_id) oi ON o.order_id = oi.order_id " +
            "WHERE o.merchant_id = #{merchantId} " +
            "AND o.order_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 30 DAY) AND CURDATE() " +
            "GROUP BY DATE(o.order_date) " +
            "ORDER BY DATE(o.order_date) DESC")
    List<SalesData> listSalesData(@Param("merchantId") int merchantId);

    @Select("SELECT DATE(o.order_date) AS orderDate, " +
            "COUNT(DISTINCT o.order_id) AS totalOrderCount, " +  // 计算总订单数
            "COUNT(DISTINCT o.user_id) AS totalCustomerCount, " +  // 计算顾客数
            "SUM(oi.quantity) AS totalSalesVolume, " +           // 计算总销量
            "SUM(o.total_amount) AS totalSalesAmount " +         // 计算总销售额
            "FROM Orders o " +
            "JOIN (SELECT order_id, SUM(quantity) AS quantity " +
            "      FROM Order_Item " +
            "      GROUP BY order_id) oi ON o.order_id = oi.order_id " +
            "WHERE o.merchant_id = #{merchantId} " +
            "AND DATE(o.order_date) = CURDATE() " +
            "GROUP BY DATE(o.order_date)")
    SalesData getCurData(@Param("merchantId") int merchantId);

    // 查询商家的名称、电话、地址和营业时间
    @Select("SELECT merchant_name AS merchantName, phone_number AS phoneNumber, merchant_address AS merchantAddress, opening_hours AS openingHours " +
            "FROM merchant " +
            "WHERE merchant_id = #{merchantId}")
    MerchantInfo getMerchantInfo(@Param("merchantId") int merchantId);

    @Select("SELECT COUNT(DISTINCT p.product_id) AS totalCategoryCount, " +
            "SUM(CASE WHEN p.available = 1 THEN 1 ELSE 0 END) AS availableProductCount, " +
            "SUM(CASE WHEN p.available = 0 THEN 1 ELSE 0 END) AS unavailableProductCount " +
            "FROM product p " +
            "WHERE p.merchant_id = #{merchantId} " +
            "AND p.is_deleted = 0")  // 仅统计未被删除的菜品
    ProductStats getProductStats(@Param("merchantId") int merchantId);

    @Select("SELECT SUM(CASE WHEN o.order_status = '1' THEN 1 ELSE 0 END) AS pendingOrderCount, " +
            "SUM(CASE WHEN o.order_status = '2' THEN 1 ELSE 0 END) AS waitingForDeliveryCount, " +
            "SUM(CASE WHEN o.order_status = '3' THEN 1 ELSE 0 END) AS deliveringCount, " +
            "SUM(CASE WHEN o.order_status = '4' THEN 1 ELSE 0 END) AS completedCount, " +
            "SUM(CASE WHEN o.order_status = '5' THEN 1 ELSE 0 END) AS canceledCount " +
            "FROM orders o " +
            "WHERE o.merchant_id = #{merchantId} " +
            "AND o.order_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 30 DAY) AND CURDATE()")
    OrderStats getMonthlyOrderStats(@Param("merchantId") int merchantId);


}









