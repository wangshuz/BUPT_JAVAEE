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


public class ProductMch {
    private Integer id;
    private String name;
    private String image;
    private Integer category_id;
    private String category_name;
    private Integer price;
    private Boolean status;

    public ProductMch() {
    }

    public ProductMch(Product p) {
        this.id = p.getId();
        this.name = p.getName();
        this.image = p.getImage();
        this.category_id = p.getCategory_id();
        this.category_name = p.getCategory_name();
        this.price = p.getPrice();
        this.status = p.getStatus();
    }

    public ProductMch(Integer id, String name, String image, Integer category_id, String category_name, Integer price, Boolean status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.category_id = category_id;
        this.category_name = category_name;
        this.price = price;
        this.status = status;
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
