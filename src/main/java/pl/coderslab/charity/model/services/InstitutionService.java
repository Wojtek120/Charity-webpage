package pl.coderslab.charity.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.dto.InstitutionDto;
import pl.coderslab.charity.model.dto.InstitutionDto;
import pl.coderslab.charity.model.entities.Institution;
import pl.coderslab.charity.model.repositories.InstitutionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InstitutionService implements ServiceInterface<InstitutionDto, Institution> {
    
    private final ModelMapper modelMapper;
    private final InstitutionRepository institutionRepository;


    public InstitutionService(ModelMapper modelMapper, InstitutionRepository institutionRepository) {
        this.modelMapper = modelMapper;
        this.institutionRepository = institutionRepository;
    }

    @Override
    public List<InstitutionDto> getAll() {
        return institutionRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<InstitutionDto> dto) {
        institutionRepository.saveAll(dto.stream().map(this::convertToEntity).collect(Collectors.toList()));
    }

    @Override
    public InstitutionDto getOne(Long id) {
        return convertToDto(institutionRepository.getOne(id));
    }

    @Override
    public Long save(InstitutionDto dto) {
        return institutionRepository.save(convertToEntity(dto)).getId();
    }

    @Override
    public void update(InstitutionDto dto) {
        institutionRepository.save(convertToEntity(dto));
    }

    @Override
    public void deleteById(Long id) {
        institutionRepository.deleteById(id);
    }

    @Override
    public InstitutionDto convertToDto(Institution entity) {
        return modelMapper.map(entity, InstitutionDto.class);
    }

    @Override
    public Institution convertToEntity(InstitutionDto dto) {
        return modelMapper.map(dto, Institution.class);
    }

    public int getNumberOfInstitutions(){
        return institutionRepository.getNumberOfInstitutions();
    }
}
