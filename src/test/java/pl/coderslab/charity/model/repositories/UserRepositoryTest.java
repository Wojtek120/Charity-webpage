package pl.coderslab.charity.model.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.model.entities.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void getByEmail() {
        //given
        String email = "user@user.pl";
        String password = "pass";
        User user = getUser(email, password);

        //when
        User result = userRepository.getByEmail(email);

        //then
        assertEquals(user.getId(), result.getId());
    }

    @Test
    public void getByEmailShouldBeNull() {
        //given
        String email = "user@user.pl";
        String password = "pass";
        User user = getUser(email, password);

        //when
        User result = userRepository.getByEmail("different@user.pl");

        //then
        assertNull(result);
    }

    @Test
    public void getPasswordByEmail() {
        //given
        String email = "user@user.pl";
        String password = "pass";
        User user = getUser(email, password);

        //when
        String resultPassword = userRepository.getPasswordByEmail(email);

        //then
        assertEquals(user.getPassword(), resultPassword);
    }

    @Test
    public void getPasswordByEmailShouldBeNull() {
        //given
        String email = "user@user.pl";
        String password = "pass";
        User user = getUser(email, password);

        //when
        String resultPassword = userRepository.getPasswordByEmail("different@user.pl");

        //then
        assertNull(resultPassword);
    }

    private User getUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        entityManager.persist(user);

        return user;
    }
}