package pl.coderslab.charity.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.dto.UserDetailsDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.embeddable.Role;
import pl.coderslab.charity.model.repositories.UserDetailsRepository;
import pl.coderslab.charity.model.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UsersAdminService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;

    public UsersAdminService(ModelMapper modelMapper, UserRepository userRepository, UserDetailsRepository userDetailsRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    public List<UserDetailsDto> getAllUsers() {

        List<User> allUsers = userRepository.findAll();
        List<UserDetailsDto> allUsersDto = new ArrayList<>();

        for(User user : allUsers) {
            if(containsRoleButNotAdmin(user.getRoles(), "ROLE_USER")) {
                allUsersDto.add(getUserDetails(user));
            }
        }

        return allUsersDto;
    }

    private boolean containsRoleButNotAdmin(Set<Role> roles, String role){

        if (roles.stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            return false;
        }

        return roles.stream().anyMatch(r -> r.getAuthority().equals(role));
    }

    private UserDetailsDto getUserDetails(User user) {
        UserDetailsDto userDetailsDto = modelMapper.map(userDetailsRepository.getByUserEmail(user.getEmail()), UserDetailsDto.class);
        userDetailsDto.setEnabled(user.getEnabled());
        userDetailsDto.setEmail(user.getEmail());

        if (userDetailsDto.getPhoneNumber() == null) {
            userDetailsDto.setPhoneNumber("-");
        }

        return userDetailsDto;
    }

}
