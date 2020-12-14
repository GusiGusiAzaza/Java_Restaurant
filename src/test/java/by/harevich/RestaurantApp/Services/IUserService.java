package by.harevich.RestaurantApp.Services;

import by.harevich.RestaurantApp.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();

    User Register(User user);
    User FindByUsername(String username);
    User FindById(Integer id);
    User FindByEmail(String email);
    User FindByPhone(String phone);

    void DeleteById(Integer id);
}
