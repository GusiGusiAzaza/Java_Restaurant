package RestaurantApp.Controllers.RestControllers;

import RestaurantApp.Services.IFoodService;
import RestaurantApp.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodRestController {

    @Autowired
    private IFoodService foodService;

    @RequestMapping(value = "{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Food>> GetAllFoods(@PathVariable(value = "page") Integer page) {
        List<Food> foods = foodService.getPage(PageRequest.of(page,8)).toList();
        if(foods.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(foods,HttpStatus.OK);
    }

}
