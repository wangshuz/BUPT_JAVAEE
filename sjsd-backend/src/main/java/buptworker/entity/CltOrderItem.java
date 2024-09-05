package buptworker.entity;

public class CltOrderItem {
    private String orderId;//order_id
    private String mchname;//merchant_name
    private String date;//order_date
    private String state;//order_status
    private String money;//total_amount

    public CltOrderItem(String orderId, String mchname, String date, String state, String money) {
        this.orderId = orderId;
        this.mchname = mchname;
        this.date = date;
        this.state = state;
        this.money = money;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMchname() {
        return mchname;
    }

    public void setMchname(String mchname) {
        this.mchname = mchname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
