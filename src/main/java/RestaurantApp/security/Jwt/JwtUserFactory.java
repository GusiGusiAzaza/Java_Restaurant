package RestaurantApp.security.Jwt;

import RestaurantApp.entity.Role;
import RestaurantApp.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(Users users) {
        return new JwtUser(
                users.getId(),
                users.getUsername(),
                users.getPhone(),
                users.getPassword(),
                users.getEmail(),
                true,
                mapToGrantedAuthorities(new ArrayList<>(users.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
