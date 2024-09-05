package buptworker.entity;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/5 15:26]
 * @updateUser : [86183]
 * @updateTime : [2024/9/5 15:26]
 * @updateRemark : [说明本次修改内容]
 */

public class MerchantInfo {
    private String merchantName;
    private String phoneNumber;
    private String merchantAddress;
    private String openingHours;

    // Getters and Setters
    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    @Override
    public String toString() {
        return "MerchantInfo{" +
                "merchantName='" + merchantName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", merchantAddress='" + merchantAddress + '\'' +
                ", openingHours='" + openingHours + '\'' +
                '}';
    }
}
