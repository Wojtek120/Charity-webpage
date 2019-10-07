package pl.coderslab.charity.model.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.dto.UserRegistrationDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.UserDetails;
import pl.coderslab.charity.model.entities.embeddable.Role;
import pl.coderslab.charity.model.repositories.UserDetailsRepository;
import pl.coderslab.charity.model.repositories.UserRepository;

import javax.transaction.Transactional;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void addNewUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        UserDetails userDetails = new UserDetails();
        String encodedPassword = passwordEncoder.encode(userRegistrationDto.getPassword());
        Role roleUser = new Role();
        roleUser.setAuthority("ROLE_USER");

        userDetails.setFirstName(userRegistrationDto.getFirstName());
        userDetails.setLastName(userRegistrationDto.getLastName());
        UserDetails savedUserDetails = userDetailsRepository.save(userDetails);

        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        user.getRoles().add(roleUser);
        user.setUserDetails(savedUserDetails);
        userRepository.save(user);

    }

}
