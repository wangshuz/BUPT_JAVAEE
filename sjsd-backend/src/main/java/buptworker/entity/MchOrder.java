package buptworker.entity;

import java.util.List;

public class MchOrder {


    //    {
//        orderID:'1332666559995544599',
//                username:'不爱吃海底捞',
//            phonenumber:'13655263459',
//            location:'北京邮电大学学十三公寓美团外卖柜',
//            ordertime:'2024-08-30 10:30:45',
//            perdicttime:'2024-08-30 11:00:45',
//            realtime:'2024-08-30 10:55:45',
//            note:'不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜不吃香菜',
//            info:[
//        {
//            name: '六神清爽小火锅',
//                    price: '198',
//                num: '2'
//        },
//
//        {
//            name: '六神清爽小火锅',
//                    price: '198',
//                num: '1'
//        }
//                        ],
//        packingCharge:'5',
//                deliveryCharge:'5',
//            totalmchpricenote:'9999',
//            // payway: 1.微信支付  2.支付宝支付
//            payway:'1',
//            money:'154',
//            orderstate:'1'
//    }
    //private MchOrderDetail mchOrderDetail;
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
    private List<MchOrderItem> info;

    public MchOrder(MchOrderDetail mchOrderDetail, List<MchOrderItem> info) {
        this.orderId = mchOrderDetail.getOrderId();
        this.username = mchOrderDetail.getUsername();
        this.phonenumber = mchOrderDetail.getPhonenumber();
        this.location = mchOrderDetail.getLocation();
        this.ordertime = mchOrderDetail.getOrdertime();
        this.perdicttime = mchOrderDetail.getPerdicttime();
        this.realtime = mchOrderDetail.getRealtime();
        this.note = mchOrderDetail.getNote();
        this.packingCharge = mchOrderDetail.getPackingCharge();
        this.deliveryCharge = mchOrderDetail.getDeliveryCharge();
        this.totalmchpricenote = mchOrderDetail.getTotalmchpricenote();
        this.payway = mchOrderDetail.getPayway();
        this.money = mchOrderDetail.getMoney();
        this.orderstate = mchOrderDetail.getOrderstate();
        this.info = info;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public List<MchOrderItem> getInfo() {
        return info;
    }

    public void setInfo(List<MchOrderItem> info) {
        this.info = info;
    }
}
