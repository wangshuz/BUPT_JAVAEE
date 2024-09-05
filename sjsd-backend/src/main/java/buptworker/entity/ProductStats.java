package buptworker.entity;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/5 15:51]
 * @updateUser : [86183]
 * @updateTime : [2024/9/5 15:51]
 * @updateRemark : [说明本次修改内容]
 */


public class ProductStats {
    private int totalCategoryCount; // 菜品种类总数
    private int availableProductCount; // 在售菜品数
    private int unavailableProductCount; // 停售菜品数

    // Getters and Setters
    public int getTotalCategoryCount() {
        return totalCategoryCount;
    }

    public void setTotalCategoryCount(int totalCategoryCount) {
        this.totalCategoryCount = totalCategoryCount;
    }

    public int getAvailableProductCount() {
        return availableProductCount;
    }

    public void setAvailableProductCount(int availableProductCount) {
        this.availableProductCount = availableProductCount;
    }

    public int getUnavailableProductCount() {
        return unavailableProductCount;
    }

    public void setUnavailableProductCount(int unavailableProductCount) {
        this.unavailableProductCount = unavailableProductCount;
    }

    @Override
    public String toString() {
        return "ProductStats{" +
                "totalCategoryCount=" + totalCategoryCount +
                ", availableProductCount=" + availableProductCount +
                ", unavailableProductCount=" + unavailableProductCount +
                '}';
    }
}
