package by.harevich.RestaurantApp.Repositories;

import by.harevich.RestaurantApp.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    Food findByTitle(String title);
}
