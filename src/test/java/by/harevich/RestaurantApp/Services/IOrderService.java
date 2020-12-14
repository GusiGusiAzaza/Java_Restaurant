package by.harevich.RestaurantApp.Services;

import by.harevich.RestaurantApp.Request.OrderRequestModel;
import by.harevich.RestaurantApp.Response.OrderResponseModel;
import by.harevich.RestaurantApp.entity.Order;
import by.harevich.RestaurantApp.entity.User;

import java.util.List;

public interface IOrderService {
    Order Add(Order order);
    Order Add(OrderRequestModel requestOrder, User user);
    Order GetById(Integer id);

    List<Order> GetAll();
    List<Order> GetByUser(User user);
    List<OrderResponseModel> GetAllResponseModel();
    List<OrderResponseModel> GetByUserResponseModel(User user);
}
