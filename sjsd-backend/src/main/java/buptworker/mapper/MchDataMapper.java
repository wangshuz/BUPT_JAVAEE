package buptworker.mapper;

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

}









