package buptworker.entity;

import java.util.List;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 18:32]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 18:32]
 * @updateRemark : [说明本次修改内容]
 */


public class CltAddress {
    private Address curAddress;
    private List<Address> allAddress;

    public CltAddress() {
    }

    public CltAddress(Address curAddress, List<Address> allAddress) {
        this.curAddress = curAddress;
        this.allAddress = allAddress;
    }

    public Address getCurAddress() {
        return curAddress;
    }

    public void setCurAddress(Address curAddress) {
        this.curAddress = curAddress;
    }

    public List<Address> getAllAddress() {
        return allAddress;
    }

    public void setAllAddress(List<Address> allAddress) {
        this.allAddress = allAddress;
    }

    @Override
    public String toString() {
        return "CltAddress{" +
                "curAddress=" + curAddress +
                ", allAddress=" + allAddress +
                '}';
    }
}
