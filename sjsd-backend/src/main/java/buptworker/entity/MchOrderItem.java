package buptworker.entity;

public class MchOrderItem {
//    {
//            name: '六神清爽小火锅',
//                    price: '198',
//                num: '2'
//        },
//

    private String name;//product_name
    private String price;//price
    private String num;//quantity

    public MchOrderItem(String name, String price, String num) {
        this.name = name;
        this.price = price;
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }


}
