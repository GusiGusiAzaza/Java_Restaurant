package RestaurantApp.Request;

import RestaurantApp.entity.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationRequestModel {

    @NotNull(message = "Username cannot be null")
    @Size(min = 6, max = 20, message = "Username must be equal or greater than 6 characters and less than 20 characters")
    private String username;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Wrong email address")
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "\\+375[0-9]{9}", message = "Wrong telephone number")
    private String phone;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 16, message = "The password must be equal or greater than 8 characters and less than 16 characters")
    private String password;

    public User ToUser(){

        return new User(
                this.getUsername(),
                this.getEmail(),
                this.getPhone(),
                this.getPassword()
        );
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
