package pl.coderslab.charity.model.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import pl.coderslab.charity.model.dto.CategoryDto;
import pl.coderslab.charity.model.dto.DonationDto;
import pl.coderslab.charity.model.dto.InstitutionDto;
import pl.coderslab.charity.model.entities.Category;
import pl.coderslab.charity.model.entities.Donation;
import pl.coderslab.charity.model.entities.Institution;
import pl.coderslab.charity.model.repositories.CategoryRepository;
import pl.coderslab.charity.model.repositories.DonationRepository;
import pl.coderslab.charity.model.repositories.InstitutionRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DonationServiceTest {

    private DonationService donationService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private DonationRepository donationRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private InstitutionRepository institutionRepository;

    @Before
    public void setUp() {
        donationService = new DonationService(modelMapper, donationRepository, categoryRepository, institutionRepository);
    }

    @Test
    public void getAllInstitutions() {
        //given
        Institution institution = new Institution();
        institution.setId(1L);
        Institution institution1 = new Institution();
        institution1.setId(2L);
        List<Institution> institutions = Arrays.asList(institution, institution1);

        InstitutionDto institutionDto = new InstitutionDto();
        institutionDto.setId(1L);
        InstitutionDto institutionDto1 = new InstitutionDto();
        institutionDto1.setId(2L);

        when(institutionRepository.findAll()).thenReturn(institutions);
        when(modelMapper.map(institution, InstitutionDto.class)).thenReturn(institutionDto);
        when(modelMapper.map(institution1, InstitutionDto.class)).thenReturn(institutionDto1);

        //when
        List<InstitutionDto> institutionDtos = donationService.getAllInstitutions();

        //then
        assertEquals(institutions.get(0).getId(), institutionDtos.get(0).getId());
        assertEquals(institutions.get(1).getId(), institutionDtos.get(1).getId());

    }

    @Test
    public void getAllCategories() {
        //given
        Category category = new Category();
        category.setId(1L);
        Category category1 = new Category();
        category1.setId(2L);
        List<Category> categories = Arrays.asList(category, category1);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setId(2L);

        when(categoryRepository.findAll()).thenReturn(categories);
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);
        when(modelMapper.map(category1, CategoryDto.class)).thenReturn(categoryDto1);

        //when
        List<CategoryDto> categoryDtos = donationService.getAllCategories();

        //then
        assertEquals(categories.get(0).getId(), categoryDtos.get(0).getId());
        assertEquals(categories.get(1).getId(), categoryDtos.get(1).getId());
    }

    @Test
    public void saveDonation() {
        DonationDto donationDto = new DonationDto();
        Donation donation = new Donation();

        when(modelMapper.map(donationDto, Donation.class)).thenReturn(donation);
        donationService.saveDonation(donationDto);
        verify(donationRepository, times(1)).save(donation);
    }
}