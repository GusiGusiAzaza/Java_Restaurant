package by.harevich.RestaurantApp.Repositories;

import by.harevich.RestaurantApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
