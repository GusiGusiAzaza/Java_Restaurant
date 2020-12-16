package RestaurantApp.Response;

import RestaurantApp.entity.FoodOrder;
import RestaurantApp.entity.Order;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrderResponseModel {

    private Integer orderId;
    private String username;
    private String phone;
    private List<Map<String,String>> foods;
    private Boolean isDelivered;

    public OrderResponseModel(Order order) {

        foods = new LinkedList<>();
        orderId = order.getId();
        username = order.getUser().getUsername();
        phone = order.getUser().getPhone();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
