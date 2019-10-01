package pl.coderslab.charity.model.dto;

import lombok.Data;
import pl.coderslab.charity.model.entities.Category;
import pl.coderslab.charity.model.entities.Institution;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
public class DonationDto {
    @Id
    private Long id;
    @NotBlank
    private int quantity;
    @NotNull
    private List<Category> categories = new ArrayList<>();
    @NotNull
    private Institution institution;
    private String street;
    private String city;
    private String zipCode;
    private Date pickUpDate;
    private Time pickUpTime;
    private String pickUpComment;
}
