package pl.coderslab.charity.model.entities;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.model.entities.base.BaseTokenEntity;

import javax.persistence.Entity;

@Entity
@Getter @Setter
public class PasswordResetToken extends BaseTokenEntity {
    public Long getUserId() {
        return user.getId();
    }
}
