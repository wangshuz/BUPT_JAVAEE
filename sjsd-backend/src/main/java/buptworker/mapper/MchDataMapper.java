package buptworker.mapper;

import buptworker.entity.SalesData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MchDataMapper {
    @Select("SELECT DATE(o.order_date) AS orderDate, " +
            "SUM(oi.quantity) AS totalSalesVolume, " +
            "SUM(o.total_amount) AS totalSalesAmount " +
            "FROM Orders o " +
            "JOIN Order_Item oi ON o.order_id = oi.order_id " +
            "WHERE o.merchant_id = #{merchantId} " +
            "AND o.order_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 30 DAY) AND CURDATE() " +
            "GROUP BY DATE(o.order_date) " +
            "ORDER BY DATE(o.order_date) DESC")
    List<SalesData> listSalesData(@Param("merchantId") int merchantId);
}









