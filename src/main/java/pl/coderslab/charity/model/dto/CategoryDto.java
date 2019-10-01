package pl.coderslab.charity.model.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
public class CategoryDto {
    @Id
    private Long id;
    @NotBlank
    private String name;
}
