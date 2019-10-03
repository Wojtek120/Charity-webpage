package pl.coderslab.charity.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.dto.CategoryDto;
import pl.coderslab.charity.model.dto.DonationDto;
import pl.coderslab.charity.model.dto.InstitutionDto;
import pl.coderslab.charity.model.entities.Category;
import pl.coderslab.charity.model.entities.Donation;
import pl.coderslab.charity.model.entities.Institution;
import pl.coderslab.charity.model.repositories.CategoryRepository;
import pl.coderslab.charity.model.repositories.DonationRepository;
import pl.coderslab.charity.model.repositories.InstitutionRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DonationFormService {

    private final ModelMapper modelMapper;
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;


    public DonationFormService(ModelMapper modelMapper, DonationRepository donationRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository) {
        this.modelMapper = modelMapper;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }



    public List<InstitutionDto> getAllInstitutions() {
        return institutionRepository.findAll().stream().map(this::institutionEntityToDto).collect(Collectors.toList());
    }


    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::categoryEntityToDto).collect(Collectors.toList());
    }

    public void saveDonation(DonationDto dto) {
        donationRepository.save(donationDtoToEntity(dto));
    }




    private InstitutionDto institutionEntityToDto(Institution entity) {
        return modelMapper.map(entity, InstitutionDto.class);
    }

    private CategoryDto categoryEntityToDto(Category entity) {
        return modelMapper.map(entity, CategoryDto.class);
    }

    private Donation donationDtoToEntity(DonationDto dto) {
        Donation donation = modelMapper.map(dto, Donation.class);

        List<Category> categories = new ArrayList<>();
        for (Long categoryId : dto.getCategories()) {
            Category category = new Category();
            category.setId(categoryId);
            categories.add(category);
        }
        donation.setCategories(categories);

        Institution institution = new Institution();
        institution.setId(dto.getInstitution());
        donation.setInstitution(institution);

        return donation;
    }

}
