package pl.coderslab.charity.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class EmailDto {
    @Email
    private String email;
}
