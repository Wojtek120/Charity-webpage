package pl.coderslab.charity.model.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.model.entities.Donation;
import pl.coderslab.charity.model.entities.Institution;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DonationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private DonationRepository donationRepository;

    @Test
    public void getQuantityOfDonatedBags() {
        //given
        Institution institution = getInstitution("name1");

        int firstQuantity = 10;
        int secondQuantity = 12;

        entityManager.persist(getDonation(firstQuantity, institution));
        entityManager.persist(getDonation(secondQuantity, institution));

        //when
        int quantity = donationRepository.getQuantityOfDonatedBags();

        //result
        assertEquals(firstQuantity + secondQuantity, quantity);

    }

    @Test
    public void getNumberOfDonatedInstitutions() {
        //given
        Institution institution = getInstitution("name1");
        Institution institution1 = getInstitution("name2");

        entityManager.persist(getDonation(1, institution));
        entityManager.persist(getDonation(1, institution));
        entityManager.persist(getDonation(1, institution1));

        //when
        int numberOfDonatedInstitutions = donationRepository.getNumberOfDonatedInstitutions();

        //result
        assertEquals(2, numberOfDonatedInstitutions);

    }

    private Donation getDonation(Integer quantity, Institution institution) {
        Donation donation = new Donation();
        donation.setQuantity(quantity);
        donation.setInstitution(institution);

        return donation;
    }

    private Institution getInstitution(String name) {
        Institution institution = new Institution();
        institution.setName(name);
        entityManager.persist(institution);

        return institution;
    }
}