package pl.coderslab.charity.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter
public class Institution extends BaseEntity {

    @Column(nullable = false)
    private String name;
    private String description;
    @OneToOne(mappedBy = "institution")
    private Donation donation;
}
