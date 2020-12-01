package by.harevich.RestaurantApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;


@Entity(name = "USERS")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"role", "order"})
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long id;
    @Column(name = "USER_USERNAME", nullable = false, length = 20)
    private String username;
    @Column(name = "USER_PASSWORD", length = 50, nullable = false)
    private String password;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ROLE_ID")
    @JsonManagedReference
    private Role role;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonBackReference
    private Collection<Order> orders;

}
