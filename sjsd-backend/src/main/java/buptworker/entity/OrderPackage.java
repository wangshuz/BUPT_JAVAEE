package buptworker.entity;

import java.util.List;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/6 14:53]
 * @updateUser : [86183]
 * @updateTime : [2024/9/6 14:53]
 * @updateRemark : [说明本次修改内容]
 */


public class OrderPackage {
    private Order order;
    private List<OrderItem> itemList;

    public OrderPackage() {
    }

    public OrderPackage(Order order, List<OrderItem> itemList) {
        this.order = order;
        this.itemList = itemList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "OrderPackage{" +
                "order=" + order +
                ", itemList=" + itemList +
                '}';
    }
}
