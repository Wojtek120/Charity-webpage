package pl.coderslab.charity.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);

    @Query(value = "SELECT password FROM users WHERE email = ?1", nativeQuery = true)
    String getPasswordByEmail(String email);

}
