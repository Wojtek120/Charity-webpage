package pl.coderslab.charity.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordDto {

    @NotBlank
    @Length(min = 8)
    private String password;

    @NotBlank
    @Length(min = 8)
    private String repeatedPassword;

}
