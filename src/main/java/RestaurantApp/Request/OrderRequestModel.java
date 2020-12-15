package RestaurantApp.Request;

import java.util.List;

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
