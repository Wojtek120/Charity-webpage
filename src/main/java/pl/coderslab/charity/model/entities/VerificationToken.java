package pl.coderslab.charity.model.entities;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.model.entities.base.BaseTokenEntity;

import javax.persistence.*;

@Entity
@Getter @Setter
public class VerificationToken extends BaseTokenEntity {

}
