package pl.coderslab.charity.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.dto.InstitutionDto;
import pl.coderslab.charity.model.entities.Institution;
import pl.coderslab.charity.model.repositories.DonationRepository;
import pl.coderslab.charity.model.repositories.InstitutionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HomeService {

    private final ModelMapper modelMapper;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public HomeService(ModelMapper modelMapper, InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.modelMapper = modelMapper;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    public List<InstitutionDto> getAllInstitutions() {
        return institutionRepository.findAll().stream().map(this::institutionEntityToDto).collect(Collectors.toList());
    }

    public int getQuantityOfDonatedBags() {
        return donationRepository.getQuantityOfDonatedBags();
    }

    private InstitutionDto institutionEntityToDto(Institution entity) {
        return modelMapper.map(entity, InstitutionDto.class);
    }

    public int getNumberOfDonatedInstitutions() {
        return donationRepository.getNumberOfDonatedInstitutions();
    }
}
