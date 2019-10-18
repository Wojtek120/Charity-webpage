package pl.coderslab.charity.model.entities;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.model.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter
public class UserDetails extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(mappedBy = "userDetails")
    private User user;
}
