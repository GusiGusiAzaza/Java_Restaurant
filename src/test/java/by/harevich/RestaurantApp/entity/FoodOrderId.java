package by.harevich.RestaurantApp.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FoodOrderId implements Serializable {

    @Column(name = "food_id")
    private int foodId;

    @Column(name = "order_id")
    private int orderId;

    public FoodOrderId(int foodId, int orderId) {
        this.foodId = foodId;
        this.orderId = orderId;
    }

    public FoodOrderId(){}

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodOrderId)) return false;
        FoodOrderId foodOrderId = (FoodOrderId) o;
        return Objects.equals(getOrderId(), foodOrderId.getOrderId()) &&
                Objects.equals(getFoodId(), foodOrderId.getFoodId());
    }
}
