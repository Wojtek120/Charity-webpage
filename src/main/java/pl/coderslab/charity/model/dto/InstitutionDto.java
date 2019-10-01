package pl.coderslab.charity.model.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
public class InstitutionDto {
    @Id
    private Long id;
    @NotBlank
    private String name;
    private String description;
}
