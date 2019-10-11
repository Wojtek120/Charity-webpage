package pl.coderslab.charity.model.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.UserDetails;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDetailsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Test
    public void getByUserEmail() {
        //given
        String email = "user@user.com";
        User user = new User();
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName("first");
        userDetails.setLastName("last");
        entityManager.persist(userDetails);

        user.setEmail(email);
        user.setPassword("pass");
        user.setUserDetails(userDetails);
        entityManager.persist(user);

        //when
        UserDetails byUserEmail = userDetailsRepository.getByUserEmail(email);

        //result
        assertEquals(userDetails.getId(), byUserEmail.getId());
    }
}