package pl.coderslab.charity.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Getter @Setter
public class Institution extends BaseEntity {

    @Column(nullable = false)
    private String name;
    private String description;
    @OneToMany(mappedBy = "institution")
    private List<Donation> donation;
}
