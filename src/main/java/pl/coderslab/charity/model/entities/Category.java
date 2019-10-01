package pl.coderslab.charity.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @ManyToMany(mappedBy = "categories")
    private List<Donation> donation = new ArrayList<>();
}
