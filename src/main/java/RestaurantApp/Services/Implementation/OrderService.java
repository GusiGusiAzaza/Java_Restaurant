package RestaurantApp.Services.Implementation;

import RestaurantApp.Repositories.OrderRepository;
import RestaurantApp.Request.FoodOrderRequestModel;
import RestaurantApp.Request.OrderRequestModel;
import RestaurantApp.Response.OrderResponseModel;
import RestaurantApp.entity.FoodOrder;
import RestaurantApp.entity.FoodOrderId;
import RestaurantApp.entity.Order;
import RestaurantApp.entity.Users;
import RestaurantApp.Services.IFoodService;
import RestaurantApp.Services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    OrderRepository orderRepository;

    @Autowired
    private IFoodService foodService;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order Add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order Add(OrderRequestModel requestOrder, Users users) {

        Order order = new Order();
        order.setUser(users);
        orderRepository.save(order);

        List<FoodOrder> foodOrders= new LinkedList<>();
        for (FoodOrderRequestModel el:requestOrder.getFoods()) {
            FoodOrder foodOrder = new FoodOrder(order,foodService.FindById(el.getFoodId()),el.getCount());
            foodOrder.setFoodOrderId(new FoodOrderId(el.getFoodId(),order.getId()));
            foodOrders.add(foodOrder);
        }
        order.setFoodOrders(foodOrders);

        return orderRepository.save(order);
    }

    @Override
    public Order GetById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> GetAll() {
        return orderRepository.findAll();
    }


    @Override
    public List<Order> GetByUser(Users users) {
        return orderRepository.findByUsers(users);
    }

    @Override
    public List<OrderResponseModel> GetAllResponseModel() {
        List<OrderResponseModel> orders = new LinkedList<>();

        for (Order el: GetAll()) {
            orders.add(new OrderResponseModel(el));
        }

        return orders;
    }

    @Override
    public Order SetDelivered(Order order) {

        order.setDelivered(true);
        Add(order);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(order.getUser().getEmail());
        message.setSubject("RestaurantApp Order");
        message.setText("Your order is ready, estimated delivery time is 20 minutes.");
        this.emailSender.send(message);

        return order;
    }

    @Override
    public List<OrderResponseModel> GetByUserResponseModel(Users users) {
        List<OrderResponseModel> orders = new LinkedList<>();

        for (Order el: GetByUser(users)) {
            orders.add(new OrderResponseModel(el));
        }

        return orders;
    }
}
