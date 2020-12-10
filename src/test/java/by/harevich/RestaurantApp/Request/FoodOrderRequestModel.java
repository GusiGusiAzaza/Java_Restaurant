package by.harevich.RestaurantApp.Request;

public class FoodOrderRequestModel {
    public Integer foodId;
    public Integer count;

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
