package RestaurantApp.Repositories;

import RestaurantApp.entity.Order;
import RestaurantApp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUsers(Users users);
}
