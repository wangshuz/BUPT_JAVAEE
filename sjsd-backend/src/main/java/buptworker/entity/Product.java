package buptworker.entity;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 14:14]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 14:14]
 * @updateRemark : [说明本次修改内容]
 */

public class Product {
    private Integer id;
    private String name;
    private String image;
    private Integer category_id;
    private String category_name;
    private Integer price;
    private String description;
    private String status;

    public Product() {
    }

    public Product(Integer id, String name, String image, Integer category_id, String category_name, Integer price, String description, String status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.category_id = category_id;
        this.category_name = category_name;
        this.price = price;
        this.description = description;
        this.status = status;
    }
}
