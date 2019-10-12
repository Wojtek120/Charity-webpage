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
public class AdminService {

    private final ModelMapper modelMapper;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public AdminService(ModelMapper modelMapper, InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.modelMapper = modelMapper;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    public List<InstitutionDto> getAllInstitutions() {
        return institutionRepository.findAll().stream().map(this::institutionEntityToDto).collect(Collectors.toList());
    }

    public InstitutionDto getInstitutionById(Long id) {
        return institutionEntityToDto(institutionRepository.getOne(id));
    }

    @Transactional
    public void saveInstitution(InstitutionDto institutionDto) {
        institutionRepository.save(institutionDtoToEntity(institutionDto));
    }

    @Transactional
    public void deleteInstitution(InstitutionDto institution) {
        donationRepository.deleteAllByInstitutionId(institution.getId());
        institutionRepository.delete(institutionDtoToEntity(institution));
    }

    private InstitutionDto institutionEntityToDto(Institution entity) {
        return modelMapper.map(entity, InstitutionDto.class);
    }

    private Institution institutionDtoToEntity(InstitutionDto dto) {
        return modelMapper.map(dto, Institution.class);
    }


}
