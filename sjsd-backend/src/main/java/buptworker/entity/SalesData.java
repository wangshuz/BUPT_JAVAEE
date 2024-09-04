package buptworker.entity;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 19:56]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 19:56]
 * @updateRemark : [说明本次修改内容]
 */


public class SalesData {
    private String orderDate; // 订单日期
    private int totalSalesVolume; // 每天的总销量
    private double totalSalesAmount; // 每天的总销售额

    // Getters and Setters
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalSalesVolume() {
        return totalSalesVolume;
    }

    public void setTotalSalesVolume(int totalSalesVolume) {
        this.totalSalesVolume = totalSalesVolume;
    }

    public double getTotalSalesAmount() {
        return totalSalesAmount;
    }

    public void setTotalSalesAmount(double totalSalesAmount) {
        this.totalSalesAmount = totalSalesAmount;
    }

    @Override
    public String toString() {
        return "SalesData{" +
                "orderDate='" + orderDate + '\'' +
                ", totalSalesVolume=" + totalSalesVolume +
                ", totalSalesAmount=" + totalSalesAmount +
                '}';
    }
}