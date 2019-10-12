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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    private AdminService adminService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private InstitutionRepository institutionRepository;
    @Mock
    private DonationRepository donationRepository;

    @Before
    public void setUp() {
        adminService = new AdminService(modelMapper, institutionRepository, donationRepository);
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
        List<InstitutionDto> institutionDtos = adminService.getAllInstitutions();

        //then
        assertEquals(institutions.get(0).getId(), institutionDtos.get(0).getId());
        assertEquals(institutions.get(1).getId(), institutionDtos.get(1).getId());

    }


    @Test
    public void getInstitutionById() {
        //given
        Institution institution = new Institution();
        institution.setId(1L);
        institution.setName("name");
        institution.setDescription("desc");

        InstitutionDto institutionDto = new InstitutionDto();
        institutionDto.setId(1L);
        institutionDto.setName("name");
        institutionDto.setDescription("desc");

        when(institutionRepository.getOne(anyLong())).thenReturn(institution);
        when(modelMapper.map(institution, InstitutionDto.class)).thenReturn(institutionDto);

        //when
        InstitutionDto result = adminService.getInstitutionById(1L);

        //then
        assertEquals(institution.getId(), result.getId());
        assertEquals(institution.getName(), result.getName());
        assertEquals(institution.getDescription(), result.getDescription());

    }

    @Test
    public void saveInstitution() {
        InstitutionDto institutionDto = new InstitutionDto();
        Institution institution = new Institution();

        when(modelMapper.map(institutionDto, Institution.class)).thenReturn(institution);
        adminService.saveInstitution(institutionDto);
        verify(institutionRepository, times(1)).save(institution);
    }

    @Test
    public void deleteInstitution() {
        InstitutionDto institutionDto = new InstitutionDto();
        Institution institution = new Institution();

        when(modelMapper.map(institutionDto, Institution.class)).thenReturn(institution);
        adminService.deleteInstitution(institutionDto);
        verify(institutionRepository, times(1)).delete(institution);
    }
}