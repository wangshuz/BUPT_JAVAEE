package buptworker.entity;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/5 11:00]
 * @updateUser : [86183]
 * @updateTime : [2024/9/5 11:00]
 * @updateRemark : [说明本次修改内容]
 */


public class Data {
    private String name;
    private Integer value;

    public Data() {
    }

    public Data(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
