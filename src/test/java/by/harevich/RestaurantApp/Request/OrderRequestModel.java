package by.harevich.RestaurantApp.Request;

import by.harevich.RestaurantApp.entity.Food;
import by.harevich.RestaurantApp.entity.FoodOrderId;
import by.harevich.RestaurantApp.entity.Order;
import by.harevich.RestaurantApp.entity.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrderRequestModel {

    List<FoodOrderRequestModel> foods;

    public List<FoodOrderRequestModel> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodOrderRequestModel> foods) {
        this.foods = foods;
    }

//    public Order AddOrderInfo(Order order)
//    {
//        List<FoodOrder> foodOrders= new LinkedList<>();
//
//        for (FoodOrderRequestModel el:foods) {
//            FoodOrder foodOrder = new FoodOrder();
//            foodOrder.setCount(el.getCount());
//            foodOrder.setFoodOrderId(new FoodOrderId(el.getFoodId(),order.getId()));
//            foodOrders.add(foodOrder);
//        }
//
//        order.setFoodOrders(foodOrders);
//        return order;
//    }
}
