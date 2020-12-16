package RestaurantApp.Controllers.RestControllers;

import RestaurantApp.Exceptions.RestValidationException;
import RestaurantApp.Request.AddFoodRequestModel;
import RestaurantApp.Request.UpdateFoodRequestModel;
import RestaurantApp.Response.OrderResponseModel;
import RestaurantApp.Services.IFoodService;
import RestaurantApp.Services.IOrderService;
import RestaurantApp.Services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import RestaurantApp.entity.Food;
import RestaurantApp.entity.Order;
import RestaurantApp.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin/")
public class AdminRestController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AdminRestController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IFoodService foodService;

    @Operation(summary = "Fetch info about all users")
    @RequestMapping(value = "users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Users>> GetAllUsers() {
        List<Users> users = userService.getAll();

        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("/api/admin/users -GET");
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @Operation(summary = "Fetch info about all orders")
    @RequestMapping(value = "orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderResponseModel>> GetAllOrders(){
        List<OrderResponseModel> orders = orderService.GetAllResponseModel();

        log.info("/api/admin/orders -GET");
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @Operation(summary = "Update order in to Delivered status")
    @RequestMapping(value = "orders/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseModel> AcceptOrder(@PathVariable(value = "id") Integer id){
        if(orderService.GetById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Order order = orderService.GetById(id);
        orderService.SetDelivered(order);


        OrderResponseModel response = new OrderResponseModel(order);

        log.info("/api/admin/orders/" + id + " -PUT");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Operation(summary = "Add food")
    @RequestMapping(value = "food", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Food> AddFood(@Valid @RequestBody AddFoodRequestModel foodDto, BindingResult errors){
        if(errors.hasErrors()){
            throw new RestValidationException(errors);
        }

        Food food = foodDto.ToFood();
        foodService.Add(food);

        log.info("/api/admin/food -POST");
        return new ResponseEntity<>(food,HttpStatus.OK);
    }

    @Operation(summary = "Update info about food")
    @RequestMapping(value = "food", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Food> UpdateFood(@Valid @RequestBody UpdateFoodRequestModel foodDto, BindingResult errors) {
        if(errors.hasErrors()){
            throw new RestValidationException(errors);
        }

        Food food = foodService.FindById(foodDto.getId());
        food.setTitle(foodDto.getTitle());
        food.setImage(foodDto.getImage());
        food.setPrice(foodDto.getPrice());
        food.setWeight(foodDto.getWeight());

        food = foodService.Add(food);

        log.info("/api/admin/food -PUT");
        return new ResponseEntity<>(food,HttpStatus.OK);
    }

    @Operation(summary = "Delete Food")
    @RequestMapping(value = "food/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Food>> DeleteFood(@PathVariable(value = "id") Integer id){
        if(foodService.FindById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        foodService.DeleteById(id);

        log.info("/api/admin/food/" + id + " -DELETE");
        return new ResponseEntity<>(foodService.getAll(),HttpStatus.OK);
    }
}
