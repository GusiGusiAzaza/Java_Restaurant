package RestaurantApp.Repositories;

import RestaurantApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String login);
    User findByEmail(String email);
    User findByPhone(String phone);
}
