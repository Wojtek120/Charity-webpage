package pl.coderslab.charity.model.entities;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.model.entities.embeddable.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled = Boolean.FALSE;

    @Column(nullable = false)
    private Boolean banned = Boolean.FALSE;

    @ElementCollection
    @CollectionTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne
    private UserDetails userDetails;

}
