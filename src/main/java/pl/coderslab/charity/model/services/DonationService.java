package pl.coderslab.charity.model.services;

import org.modelmapper.ModelMapper;
import pl.coderslab.charity.model.dto.DonationDto;
import pl.coderslab.charity.model.entities.Donation;
import pl.coderslab.charity.model.repositories.DonationRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DonationService implements ServiceInterface<DonationDto, Donation> {

    private final ModelMapper modelMapper;
    private final DonationRepository donationRepository;


    public DonationService(ModelMapper modelMapper, DonationRepository donationRepository) {
        this.modelMapper = modelMapper;
        this.donationRepository = donationRepository;
    }

    @Override
    public List<DonationDto> getAll() {
        return donationRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<DonationDto> dto) {
        donationRepository.saveAll(dto.stream().map(this::convertToEntity).collect(Collectors.toList()));
    }

    @Override
    public DonationDto getOne(Long id) {
        return convertToDto(donationRepository.getOne(id));
    }

    @Override
    public Long save(DonationDto dto) {
        return donationRepository.save(convertToEntity(dto)).getId();
    }

    @Override
    public void update(DonationDto dto) {
        donationRepository.save(convertToEntity(dto));
    }

    @Override
    public void deleteById(Long id) {
        donationRepository.deleteById(id);
    }

    @Override
    public DonationDto convertToDto(Donation entity) {
        return modelMapper.map(entity, DonationDto.class);
    }

    @Override
    public Donation convertToEntity(DonationDto dto) {
        return modelMapper.map(dto, Donation.class);
    }

}
