package RestaurantApp.Repositories;

import RestaurantApp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String login);
    Users findByEmail(String email);
    Users findByPhone(String phone);
}
