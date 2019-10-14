package pl.coderslab.charity.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;
import pl.coderslab.charity.utils.annotations.ValidPassword;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegistrationDto {

    @Id
    Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 8)
    @ValidPassword
    private String password;

    @NotBlank
    @Length(min = 8)
    @ValidPassword
    private String repeatedPassword;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NumberFormat
    private String phoneNumber;
}

