package by.harevich.RestaurantApp.Response;

import by.harevich.RestaurantApp.entity.FoodOrder;
import by.harevich.RestaurantApp.entity.Order;

import java.util.*;

public class OrderResponseModel {

    private String username;
    private List<Map<String,String>> foods;
    private Boolean isDelivered;

    public OrderResponseModel(Order order) {

        foods = new LinkedList<>();
        username = order.getUser().getUsername();
        isDelivered = order.getDelivered();

        for(FoodOrder el: order.getFoodOrders()){
            Map<String,String> food = new LinkedHashMap<>();
            food.put("Title",el.getFood().getTitle());
            food.put("Count",el.getCount().toString());
            foods.add(food);
        }
    }

    public Boolean getDelivered() {
        return isDelivered;
    }

    public void setDelivered(Boolean delivered) {
        isDelivered = delivered;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Map<String, String>> getFoods() {
        return foods;
    }

    public void setFoods(List<Map<String, String>> foods) {
        this.foods = foods;
    }
}
