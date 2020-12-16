package by.harevich.RestaurantApp;

import RestaurantApp.Repositories.FoodRepository;
import RestaurantApp.Repositories.OrderRepository;
import RestaurantApp.Repositories.UserRepository;
import RestaurantApp.entity.Food;
import RestaurantApp.entity.FoodOrder;
import RestaurantApp.entity.Order;
import RestaurantApp.entity.Users;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantAppTest {
    @MockBean
    private FoodRepository foodRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAddFood() {
        List<Food> foods = Arrays.asList(
                new Food("C:\\main\\5SEM\\JAVA\\RestaurantApp\\src\\main\\resources\\static\\Images\\pizza.jpg",
                        "Pizza", 14.50, 870.5),
                new Food("C:\\main\\5SEM\\JAVA\\RestaurantApp\\src\\main\\resources\\static\\Images\\apple.jpg",
                        "Apple", 1.49, 120.5)
        );
        when(foodRepository.findAll()).thenReturn(foods);

        Assert.assertEquals(foodRepository.findAll(), foods);
    }

    @Test
    public void testGetFood() throws Exception {
        setUp();
        List<Food> foods = Arrays.asList(
                new Food("C:\\main\\5SEM\\JAVA\\RestaurantApp\\src\\main\\resources\\static\\Images\\pizza.jpg",
                        "Pizza", 14.50, 870.5),
                new Food("C:\\main\\5SEM\\JAVA\\RestaurantApp\\src\\main\\resources\\static\\Images\\apple.jpg",
                        "Apple", 1.49, 120.5)
        );

        when(foodRepository.findAll()).thenReturn(foods);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/foods"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[*].title", Matchers.containsInAnyOrder("pizza", "apple")));
    }

    @Test
    public void testAddUsers() {
        List<Users> users = Arrays.asList(
                new Users("testuser1", "testuser1@mail.com" , "+375291384516", "few0m5348g50n2"),
                new Users("testuser2", "testuser2@mail.com" , "+373292384416", "w45g3208mg5n25")
        );
        when(userRepository.findAll()).thenReturn(users);

        Assert.assertEquals(userRepository.findAll(), users);
    }

    @Test
    public void testGetUsers() throws Exception {
        setUp();
        List<Users> users = Arrays.asList(
                new Users("testuser1", "testuser1@mail.com" , "+375291384516", "few0m5348g50n2"),
                new Users("testuser2", "testuser2@mail.com" , "+373292384416", "w45g3208mg5n25")
        );

        when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[*].username", Matchers.containsInAnyOrder("testuser1", "testuser2")));
    }
}
