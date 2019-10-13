package pl.coderslab.charity.model.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.VerificationToken;

import java.sql.Timestamp;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VerificationTokenRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Test
    public void getByToken() {
        //given
        User user = getAndSaveUser();

        Long id = 1L;
        String token = "token";
        Timestamp expiryDate = new Timestamp(System.currentTimeMillis());

        VerificationToken verificationToken = getAndSaveToken(id, token, expiryDate, user);

        //when
        VerificationToken result = verificationTokenRepository.getByToken(token);

        //then
        assertEquals(verificationToken.getId(), result.getId());
        assertEquals(verificationToken.getToken(), result.getToken());
        assertEquals(verificationToken.getExpiryDate(), result.getExpiryDate());
        assertEquals(verificationToken.getUser().getId(), result.getUser().getId());
    }

    @Test
    public void getByToken_wrongToken_shouldBeNull() {
        //given
        User user = getAndSaveUser();

        Long id = 1L;
        String token = "token";
        Timestamp expiryDate = new Timestamp(System.currentTimeMillis());

        VerificationToken verificationToken = getAndSaveToken(id, token, expiryDate, user);

        //when
        VerificationToken result = verificationTokenRepository.getByToken("differentToken");

        //then
        assertNull(result);
    }

    private VerificationToken getAndSaveToken(Long id, String token, Timestamp expiryDate, User user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setId(id);
        verificationToken.setToken(token);
        verificationToken.setExpiryDate(expiryDate);
        verificationToken.setUser(user);
        return entityManager.merge(verificationToken);
    }

    private User getAndSaveUser() {
        User user = new User();
        user.setEmail("@");
        user.setPassword("@");
        return entityManager.merge(user);
    }
}