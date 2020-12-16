package by.harevich.RestaurantApp;

import RestaurantApp.entity.Role;
import RestaurantApp.security.Jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IntegrationTests {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private MockMvc mockMvc;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void checkToken() throws Exception {
        setUp();
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("USER");
        roles.add(role);
        String token = jwtTokenProvider.createToken("user1338", roles);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        System.out.println(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/order").header("Authorization", "Bearer_" + token))
                .andExpect(status().isOk());
    }

    @Test
    void checkAdmin() throws Exception {
        setUp();
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("ADMIN");
        roles.add(role);
        String token = jwtTokenProvider.createToken("testAdmin", roles);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/users").header("Authorization", "Bearer_" + token))
                .andExpect(status().isOk());
    }


}