package buptworker.entity;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/5 16:15]
 * @updateUser : [86183]
 * @updateTime : [2024/9/5 16:15]
 * @updateRemark : [说明本次修改内容]
 */


public class OrderStats {
    private int pendingOrderCount;  // 待接单订单数
    private int waitingForDeliveryCount;  // 待派送订单数
    private int deliveringCount;  // 派送中订单数
    private int completedCount;  // 已完成订单数
    private int canceledCount;  // 已取消订单数

    // Getters and Setters
    public int getPendingOrderCount() {
        return pendingOrderCount;
    }

    public void setPendingOrderCount(int pendingOrderCount) {
        this.pendingOrderCount = pendingOrderCount;
    }

    public int getWaitingForDeliveryCount() {
        return waitingForDeliveryCount;
    }

    public void setWaitingForDeliveryCount(int waitingForDeliveryCount) {
        this.waitingForDeliveryCount = waitingForDeliveryCount;
    }

    public int getDeliveringCount() {
        return deliveringCount;
    }

    public void setDeliveringCount(int deliveringCount) {
        this.deliveringCount = deliveringCount;
    }

    public int getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }

    public int getCanceledCount() {
        return canceledCount;
    }

    public void setCanceledCount(int canceledCount) {
        this.canceledCount = canceledCount;
    }

    @Override
    public String toString() {
        return "OrderStats{" +
                "pendingOrderCount=" + pendingOrderCount +
                ", waitingForDeliveryCount=" + waitingForDeliveryCount +
                ", deliveringCount=" + deliveringCount +
                ", completedCount=" + completedCount +
                ", canceledCount=" + canceledCount +
                '}';
    }
}