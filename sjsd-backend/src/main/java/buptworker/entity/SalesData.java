package buptworker.entity;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 19:56]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 19:56]
 * @updateRemark : [说明本次修改内容]
 */


public class SalesData {
    private String orderDate; // 订单日期
    private int totalSalesVolume; // 总销量
    private double totalSalesAmount; // 总销售额
    private int totalOrderCount; // 总订单数
    private int totalCustomerCount; // 总顾客数

    public SalesData(String orderDate, int totalSalesVolume, double totalSalesAmount, int totalOrderCount, int totalCustomerCount) {
        this.orderDate = orderDate;
        this.totalSalesVolume = totalSalesVolume;
        this.totalSalesAmount = totalSalesAmount;
        this.totalOrderCount = totalOrderCount;
        this.totalCustomerCount = totalCustomerCount;
    }

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

    public int getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(int totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public int getTotalCustomerCount() {
        return totalCustomerCount;
    }

    public void setTotalCustomerCount(int totalCustomerCount) {
        this.totalCustomerCount = totalCustomerCount;
    }

    @Override
    public String toString() {
        return "SalesData{" +
                "orderDate='" + orderDate + '\'' +
                ", totalSalesVolume=" + totalSalesVolume +
                ", totalSalesAmount=" + totalSalesAmount +
                ", totalOrderCount=" + totalOrderCount +
                ", totalCustomerCount=" + totalCustomerCount +
                '}';
    }
}