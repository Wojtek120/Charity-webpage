package pl.coderslab.charity.model.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.dto.UserDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.embeddable.Role;
import pl.coderslab.charity.model.repositories.UserRepository;

import javax.transaction.Transactional;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void addNewUser(UserDto userDto) {
        User user = new User();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        Role roleUser = new Role();
        roleUser.setAuthority("ROLE_USER");

        user.setEmail(userDto.getEmail());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        user.getRoles().add(roleUser);

        userRepository.save(user);
    }

}
