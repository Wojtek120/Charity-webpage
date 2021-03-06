package pl.coderslab.charity.model.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import pl.coderslab.charity.model.dto.InstitutionDto;
import pl.coderslab.charity.model.entities.Institution;
import pl.coderslab.charity.model.repositories.DonationRepository;
import pl.coderslab.charity.model.repositories.InstitutionRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeServiceTest {

    private HomeService homeService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private InstitutionRepository institutionRepository;
    @Mock
    private DonationRepository donationRepository;

    @Before
    public void setUp() {
        homeService = new HomeService(modelMapper, institutionRepository, donationRepository);
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
        List<InstitutionDto> institutionDtos = homeService.getAllInstitutions();

        //then
        assertEquals(institutions.get(0).getId(), institutionDtos.get(0).getId());
        assertEquals(institutions.get(1).getId(), institutionDtos.get(1).getId());

    }
}