package pl.coderslab.charity.model.entities;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.model.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Getter @Setter
public class Institution extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    @OneToMany(mappedBy = "institution")
    private List<Donation> donation;
}
