package by.harevich.RestaurantApp.Services;

import by.harevich.RestaurantApp.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFoodService {

    Food Add(Food food);

    List<Food> getAll();

    Page<Food> getPage(Pageable pageable);

    Food FindByName(String name);

    Food FindById(Integer id);

    void DeleteById(Integer id);
}
