package buptworker.entity;

public class CltOrderInfo {
    private String picture;//imageUrl
    private String name;//product_name
    private String price;//price
    private String num;//quantity

    public CltOrderInfo(String picture, String name, String price, String num) {
        this.picture = picture;
        this.name = name;
        this.price = price;
        this.num = num;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }


}
