package buptworker.entity;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [商家类型]
 * @createTime : [2024/9/3 20:19]
 * @updateUser : [86183]
 * @updateTime : [2024/9/3 20:19]
 * @updateRemark : [说明本次修改内容]
 */


public class MchType {
    private Integer typeId;
    private String typeName;

    public MchType(Integer typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "MchType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
