package RestaurantApp.Services;

import RestaurantApp.Request.OrderRequestModel;
import RestaurantApp.Response.OrderResponseModel;
import RestaurantApp.entity.Order;
import RestaurantApp.entity.User;

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
