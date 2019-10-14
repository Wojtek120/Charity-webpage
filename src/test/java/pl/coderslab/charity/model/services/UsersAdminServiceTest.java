package pl.coderslab.charity.model.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.charity.model.dto.InstitutionDto;
import pl.coderslab.charity.model.dto.UserDetailsDto;
import pl.coderslab.charity.model.entities.Institution;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.UserDetails;
import pl.coderslab.charity.model.entities.embeddable.Role;
import pl.coderslab.charity.model.repositories.DonationRepository;
import pl.coderslab.charity.model.repositories.UserDetailsRepository;
import pl.coderslab.charity.model.repositories.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsersAdminServiceTest {

    private UsersAdminService usersAdminService;

    private ModelMapper modelMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDetailsRepository userDetailsRepository;

    @Before
    public void setUp() {
        modelMapper = new ModelMapper();
        usersAdminService = new UsersAdminService(modelMapper, userRepository, userDetailsRepository);
    }

    @Test
    public void getAllUsers() {
        //given
        Set<Role> roles = new HashSet<>(Arrays.asList(getRole("ROLE_USER")));
        User user = getUser(1L, roles);

        Set<Role> roles1 = new HashSet<>(Arrays.asList(getRole("ROLE_USER"), getRole("ROLE_ADMIN")));
        User user1 = getUser(1L, roles1);

        List<User> users = Arrays.asList(user, user1);

        InstitutionDto institutionDto = new InstitutionDto();
        institutionDto.setId(1L);
        InstitutionDto institutionDto1 = new InstitutionDto();
        institutionDto1.setId(2L);

        when(userRepository.findAll()).thenReturn(users);
        when(userDetailsRepository.getByUserEmail(user.getEmail())).thenReturn(new UserDetails());

        //when
        List<UserDetailsDto> result = usersAdminService.getAllUsers();

        //then
        assertEquals(result.size(), 1);
    }

    private Role getRole(String authority) {
        Role role = new Role();
        role.setAuthority(authority);
        return role;
    }

    private User getUser(Long id, Set<Role> roles) {
        User user = new User();
        user.setId(id);
        user.setEnabled(true);
//        UserDetails userDetails = new UserDetails();
//        userDetails.setUser(user);
//        user.setUserDetails(userDetails);
        user.setRoles(roles);

        return user;
    }
}