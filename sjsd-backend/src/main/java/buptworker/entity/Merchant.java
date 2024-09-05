package buptworker.entity;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/4 16:23]
 * @updateUser : [86183]
 * @updateTime : [2024/9/4 16:23]
 * @updateRemark : [说明本次修改内容]
 */


public class Merchant {
    private int merchantID;
    private String avatarURL;
    private String merchantName;
    private int typeId;
    private double deliveryFee;
    private double packagingFeePerItem;
    private double minimumOrderAmount;
    private String openingHours;
    private String phoneNumber;
    private String merchantAddress;
    private String merchantDescription;
    private boolean isOpen;

    public Merchant() {
    }

    public Merchant(int merchantID, String avatarURL, String merchantName, int typeId, double deliveryFee, double packagingFeePerItem, double minimumOrderAmount, String openingHours, String phoneNumber, String merchantAddress, String merchantDescription) {
        this.merchantID = merchantID;
        this.merchantName = merchantName;
        this.avatarURL = avatarURL;
        this.merchantAddress = merchantAddress;
        this.phoneNumber = phoneNumber;
        this.openingHours = openingHours;
        this.merchantDescription = merchantDescription;
        this.deliveryFee = deliveryFee;
        this.minimumOrderAmount = minimumOrderAmount;
        this.packagingFeePerItem = packagingFeePerItem;
        this.typeId = typeId;

    }
    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public int getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(int merchantID) {
        this.merchantID = merchantID;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /*public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }*/

    public int getTypeId() {
        return typeId;
    }

    public void setTypeName(int typeId) {
        this.typeId = typeId;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getPackagingFeePerItem() {
        return packagingFeePerItem;
    }

    public void setPackagingFeePerItem(double packagingFeePerItem) {
        this.packagingFeePerItem = packagingFeePerItem;
    }

    public double getMinimumOrderAmount() {
        return minimumOrderAmount;
    }

    public void setMinimumOrderAmount(double minimumOrderAmount) {
        this.minimumOrderAmount = minimumOrderAmount;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
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

    public String getMerchantDescription() {
        return merchantDescription;
    }

    public void setMerchantDescription(String merchantDescription) {
        this.merchantDescription = merchantDescription;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantID=" + merchantID +
                ", avatarURL='" + avatarURL + '\'' +
                ", merchantName='" + merchantName + '\'' +
               // ", typeName='" + typeName + '\'' +
                ", deliveryFee=" + deliveryFee +
                ", packagingFeePerItem=" + packagingFeePerItem +
                ", minimumOrderAmount=" + minimumOrderAmount +
                ", openingHours='" + openingHours + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", merchantAddress='" + merchantAddress + '\'' +
                ", merchantDescription='" + merchantDescription + '\'' +
                '}';
    }
}