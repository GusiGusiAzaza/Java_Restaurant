package RestaurantApp.Services;

import RestaurantApp.entity.Users;

import java.util.List;

public interface IUserService {
    List<Users> getAll();

    Users Register(Users users);
    Users FindByUsername(String username);
    Users FindById(Integer id);
    Users FindByEmail(String email);
    Users FindByPhone(String phone);

    void DeleteById(Integer id);
}
