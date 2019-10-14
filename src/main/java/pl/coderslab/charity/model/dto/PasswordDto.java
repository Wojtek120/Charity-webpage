package pl.coderslab.charity.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pl.coderslab.charity.utils.annotations.ValidPassword;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordDto {

    @NotBlank
    @Length(min = 8)
    @ValidPassword
    private String password;

    @NotBlank
    @Length(min = 8)
    @ValidPassword
    private String repeatedPassword;

}
