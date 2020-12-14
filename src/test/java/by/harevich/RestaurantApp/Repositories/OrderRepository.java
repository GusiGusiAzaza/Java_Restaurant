package by.harevich.RestaurantApp.Repositories;

import by.harevich.RestaurantApp.entity.Order;
import by.harevich.RestaurantApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
}
