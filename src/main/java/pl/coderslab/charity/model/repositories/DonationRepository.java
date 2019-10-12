package pl.coderslab.charity.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.entities.Donation;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM(d.quantity) FROM Donation d")
    int getQuantityOfDonatedBags();

    @Query("SELECT COUNT ( DISTINCT d.institution) FROM Donation d")
    int getNumberOfDonatedInstitutions();

    void deleteAllByInstitutionId(Long id);
}
