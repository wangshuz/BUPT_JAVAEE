package buptworker.mapper;

import buptworker.entity.MchIntro;
import buptworker.entity.MchType;
import buptworker.entity.TopPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper //运行时会自动生成该接口的实现类对象（代理对象），并交给IOC管理
public interface CltMainMapper {
    @Select("SELECT merchant_id as id,merchant_name as name,merchant_description as intro,type_id as label,merchant_avatar as url FROM merchant")
    public List<MchIntro> listMchIntro();

    @Select("SELECT type_id AS typeId, type_name AS typeName FROM merchant_type")
    public List<MchType> listMchType();

    @Select("WITH FrequentMerchantTypes AS ( "
            + "    SELECT mt.type_id, COUNT(o.order_id) AS order_count "
            + "    FROM Orders o "
            + "    JOIN Merchant m ON o.merchant_id = m.merchant_id "
            + "    JOIN Merchant_Type mt ON m.type_id = mt.type_id "
            + "    WHERE o.user_id = #{userId} "
            + "    GROUP BY mt.type_id "
            + "    ORDER BY order_count DESC "
            + "    LIMIT 3 "
            + "), "
            + "MonthlySales AS ( "
            + "    SELECT m.type_id, o.merchant_id, COUNT(oi.order_item_id) AS total_sales "
            + "    FROM Orders o "
            + "    JOIN Order_Item oi ON o.order_id = oi.order_id "
            + "    JOIN Merchant m ON o.merchant_id = m.merchant_id "
            + "    WHERE o.order_date >= DATE_SUB(NOW(), INTERVAL 30 DAY) "
            + "    GROUP BY m.type_id, o.merchant_id "
            + ") "
            + "SELECT m.merchant_id AS id, m.merchant_avatar AS url "
            + "FROM FrequentMerchantTypes fmt "
            + "JOIN MonthlySales ms ON fmt.type_id = ms.type_id "
            + "JOIN Merchant m ON ms.merchant_id = m.merchant_id "
            + "WHERE ms.total_sales = ( "
            + "    SELECT MAX(ms2.total_sales) "
            + "    FROM MonthlySales ms2 "
            + "    WHERE ms2.type_id = fmt.type_id "
            + ")")
    List<TopPicture> listTopPicture(@Param("userId") int userId);

}
