package RestaurantApp.Services.Implementation;

import RestaurantApp.Repositories.RoleRepository;
import RestaurantApp.Repositories.UserRepository;
import RestaurantApp.entity.Role;
import RestaurantApp.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import RestaurantApp.Services.IUserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users Register(Users users) {

        Role roleUser = roleRepository.findByName("USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        users.setRoles(userRoles);
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        return userRepository.save(users);
    }

    @Override
    public List<Users> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Users FindByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users FindById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Users FindByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users FindByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public void DeleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
