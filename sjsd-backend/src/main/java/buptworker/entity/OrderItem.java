package buptworker.entity;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/6 14:42]
 * @updateUser : [86183]
 * @updateTime : [2024/9/6 14:42]
 * @updateRemark : [说明本次修改内容]
 */


public class OrderItem {
    private Integer order_id;
    private Integer product_id;
    private Integer quantity;

    public OrderItem() {
    }

    public OrderItem(Integer order_id, Integer product_id, Integer quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "order_id=" + order_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                '}';
    }
}
