package pl.coderslab.charity.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Donation extends BaseEntity {

    @Column(nullable = false)
    private int quantity;
    @ManyToMany
    private List<Category> categories = new ArrayList<>();
    @OneToOne
    private Institution institution;
    private String street;
    private String city;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "pick_up_date")
    private Date pickUpDate;
    @Column(name = "pick_up_time")
    private Time pickUpTime;
    @Column(name = "pick_up_comment")
    private String pickUpComment;
}
