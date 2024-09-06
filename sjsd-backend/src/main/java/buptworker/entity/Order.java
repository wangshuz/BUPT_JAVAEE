package buptworker.entity;

import java.math.BigDecimal;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/6 14:38]
 * @updateUser : [86183]
 * @updateTime : [2024/9/6 14:38]
 * @updateRemark : [说明本次修改内容]
 */


public class Order {
    private Integer orderId;
    private Integer merchantId;
    private Integer userId;
    private Integer addressId;
    private String paymentMethod;
    private BigDecimal packagingFee;
    private BigDecimal deliveryFee;
    private String orderStatus;
    private String notes;
    private BigDecimal totalAmount;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    // Getters and Setters
    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getPackagingFee() {
        return packagingFee;
    }

    public void setPackagingFee(BigDecimal packagingFee) {
        this.packagingFee = packagingFee;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}