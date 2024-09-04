package buptworker.entity;

import java.util.List;

public class MchOrder {
    public String getMchname() {
        return mchname;
    }

    public void setMchname(String mchname) {
        this.mchname = mchname;
    }

    public List<MchOrderItem> getInfo() {
        return info;
    }

    public void setInfo(List<MchOrderItem> info) {
        this.info = info;
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

    private String mchname;
    private List<MchOrderItem> info;
    private String date;
    private String state;
    private String money;

    // Getters and Setters
}