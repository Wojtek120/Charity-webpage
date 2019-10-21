package pl.coderslab.charity.model.entities.base;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.model.entities.User;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseTokenEntity extends BaseEntity implements Serializable {
    private static final int EXPIRATION_HOURS = 24;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    protected User user;

    private Timestamp expiryDate;

    public BaseTokenEntity() {
        calculateExpiryDate();
    }

    public void calculateExpiryDate() {
        int expirationTime = EXPIRATION_HOURS * 60 * 60 * 1000;
        this.expiryDate = new Timestamp(System.currentTimeMillis() + expirationTime);
    }

    public boolean isValid() {
        return expiryDate.getTime() - System.currentTimeMillis() >= 0;
    }
}
