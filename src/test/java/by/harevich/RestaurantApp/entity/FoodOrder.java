package by.harevich.RestaurantApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "orders_foods")
public class FoodOrder {
    @EmbeddedId
    private FoodOrderId foodOrderId = new FoodOrderId();

    @ManyToOne
    @MapsId("id")
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("id")
    @JsonIgnore
    @JoinColumn(name = "food_id")
    private Food food;

    private Integer count;

    public FoodOrder(Order order, Food food, Integer count) {
        this.order = order;
        this.food = food;
        this.count = count;
    }

    public FoodOrder(){}

    public FoodOrderId getFoodOrderId() {
        return foodOrderId;
    }

    public void setFoodOrderId(FoodOrderId foodOrderId) {
        this.foodOrderId = foodOrderId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
