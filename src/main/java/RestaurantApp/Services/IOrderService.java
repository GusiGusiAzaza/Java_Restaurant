package RestaurantApp.Services;

import RestaurantApp.Request.OrderRequestModel;
import RestaurantApp.Response.OrderResponseModel;
import RestaurantApp.entity.Order;
import RestaurantApp.entity.Users;

import java.util.List;

public interface IOrderService {
    Order Add(Order order);
    Order Add(OrderRequestModel requestOrder, Users users);
    Order GetById(Integer id);

    List<Order> GetAll();
    List<Order> GetByUser(Users users);
    List<OrderResponseModel> GetAllResponseModel();
    List<OrderResponseModel> GetByUserResponseModel(Users users);

    Order SetDelivered(Order order);
}
