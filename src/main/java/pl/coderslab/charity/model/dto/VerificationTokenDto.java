package pl.coderslab.charity.model.dto;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class VerificationTokenDto {

    @Id
    private Long id;

    @NotBlank
    private String token;

    @NotNull
    private Long userId;

    private Timestamp expiryDate;
}
