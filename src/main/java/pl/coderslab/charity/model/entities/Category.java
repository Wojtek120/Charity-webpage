package pl.coderslab.charity.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @ManyToMany(mappedBy = "categories")
    private Donation donation;
}
