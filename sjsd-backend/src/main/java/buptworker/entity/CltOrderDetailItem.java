package buptworker.entity;

public class CltOrderDetailItem {

    private String mchname;//merchant_name
    private String mchId;//merchant_id
    private String address;//address_line
    private String note;//notes
    private String packingCharge;//packaging_fee
    private String deliveryCharge;//delivery_fee
    private String orderdate;//order_date
    private String predictdate;//estimated_delivery_time
    private String realdate;//actual_delivery_time
    private String payway;//payment_method
    private String state;//order_status
    private String pdtmoney;//total_amount - packaging_fee - delivery_fee
    private String totalmoney;//total_amount


    public CltOrderDetailItem(String mchname, String mchId, String address, String note, String packingCharge, String deliveryCharge, String orderdate, String predictdate, String realdate, String payway, String state, String pdtmoney, String totalmoney) {
        this.mchname = mchname;
        this.mchId = mchId;
        this.address = address;
        this.note = note;
        this.packingCharge = packingCharge;
        this.deliveryCharge = deliveryCharge;
        this.orderdate = orderdate;
        this.predictdate = predictdate;
        this.realdate = realdate;
        this.payway = payway;
        this.state = state;
        this.pdtmoney = pdtmoney;
        this.totalmoney = totalmoney;
    }

    public String getMchname() {
        return mchname;
    }

    public void setMchname(String mchname) {
        this.mchname = mchname;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getPredictdate() {
        return predictdate;
    }

    public void setPredictdate(String predictdate) {
        this.predictdate = predictdate;
    }

    public String getRealdate() {
        return realdate;
    }

    public void setRealdate(String realdate) {
        this.realdate = realdate;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPdtmoney() {
        return pdtmoney;
    }

    public void setPdtmoney(String pdtmoney) {
        this.pdtmoney = pdtmoney;
    }

    public String getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(String totalmoney) {
        this.totalmoney = totalmoney;
    }
}
