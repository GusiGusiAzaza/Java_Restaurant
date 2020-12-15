package RestaurantApp.Services.Implementation;

import RestaurantApp.Repositories.FoodRepository;
import RestaurantApp.entity.Food;
import RestaurantApp.Services.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService implements IFoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food Add(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public List<Food> getAll() {
        return foodRepository.findAll();
    }

    @Override
    public Page<Food> getPage(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    @Override
    public Food FindByName(String name) {
        return foodRepository.findByTitle(name);
    }

    @Override
    public Food FindById(Integer id) {
        return foodRepository.findById(id).orElse(null);
    }

    @Override
    public void DeleteById(Integer id) {
        foodRepository.deleteById(id);
    }
}
