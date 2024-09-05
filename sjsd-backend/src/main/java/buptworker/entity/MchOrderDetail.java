package buptworker.entity;

public class MchOrderDetail {

    private String orderId;//order_id
    private String username;//username
    private String phonenumber;//recipient_phone
    private String location;//address_line
    private String ordertime;//order_date
    private String perdicttime;//estimated_delivery_time
    private String realtime;//actual_delivery_time
    private String note;//notes
    private String packingCharge;//packaging_fee
    private String deliveryCharge;//delivery_fee
    private String totalmchpricenote;//total_amount - packaging_fee - delivery_fee
    private String payway;//payment_method
    private String money;//total_amount
    private String orderstate;//order_status

    public MchOrderDetail(String orderId, String username, String phonenumber, String location, String ordertime, String perdicttime, String realtime, String note, String packingCharge, String deliveryCharge, String totalmchpricenote, String payway, String money, String orderstate) {
        this.orderId = orderId;
        this.username = username;
        this.phonenumber = phonenumber;
        this.location = location;
        this.ordertime = ordertime;
        this.perdicttime = perdicttime;
        this.realtime = realtime;
        this.note = note;
        this.packingCharge = packingCharge;
        this.deliveryCharge = deliveryCharge;
        this.totalmchpricenote = totalmchpricenote;
        this.payway = payway;
        this.money = money;
        this.orderstate = orderstate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getPerdicttime() {
        return perdicttime;
    }

    public void setPerdicttime(String perdicttime) {
        this.perdicttime = perdicttime;
    }

    public String getRealtime() {
        return realtime;
    }

    public void setRealtime(String realtime) {
        this.realtime = realtime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPackingCharge() {
        return packingCharge;
    }

    public void setPackingCharge(String packingCharge) {
        this.packingCharge = packingCharge;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getTotalmchpricenote() {
        return totalmchpricenote;
    }

    public void setTotalmchpricenote(String totalmchpricenote) {
        this.totalmchpricenote = totalmchpricenote;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(String orderstate) {
        this.orderstate = orderstate;
    }


}
