package buptworker.entity;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 15:23]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 15:23]
 * @updateRemark : [说明本次修改内容]
 */


public class ProductClt {
    private Integer id;
    private String name;
    private Integer price;
    private String image;
    private Integer category_id;
    private String description;

    public ProductClt() {
    }

    public ProductClt(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.image = product.getImage();
        this.category_id = product.getCategory_id();
        this.description = product.getDescription();
    }

    public ProductClt(Integer id, String name, Integer price, String image, Integer category_id, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category_id = category_id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
