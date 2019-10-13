package pl.coderslab.charity.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
@Getter @Setter
public class VerificationToken extends BaseEntity{

    private static final int EXPIRATION_HOURS = 24;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Timestamp expiryDate;

    public VerificationToken() {
        calculateExpiryDate();
    }

    private void calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        int expirationTime = EXPIRATION_HOURS * 60 * 60 * 1000;
        this.expiryDate = new Timestamp(calendar.getTimeInMillis() + expirationTime);
    }
}
