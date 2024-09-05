package buptworker.entity;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 18:26]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 18:26]
 * @updateRemark : [说明本次修改内容]
 */


public class Address {
    private Integer id;
    private String name;
    private String phone;
    private String address;
    private Boolean is_default;

    public Address() {
    }

    public Address(Integer id ,String name, String phone, String address, Boolean is_default) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.is_default = is_default;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIs_default() {
        return is_default;
    }

    public void setIs_default(Boolean is_default) {
        this.is_default = is_default;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
