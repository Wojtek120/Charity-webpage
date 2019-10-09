package pl.coderslab.charity.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.model.entities.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    UserDetails getByUserEmail(String email);
}
