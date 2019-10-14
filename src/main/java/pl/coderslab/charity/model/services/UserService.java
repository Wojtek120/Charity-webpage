package pl.coderslab.charity.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.charity.events.OnRegistrationCompleteEvent;
import pl.coderslab.charity.model.dto.PasswordDto;
import pl.coderslab.charity.model.dto.UserDetailsDto;
import pl.coderslab.charity.model.dto.UserRegistrationDto;
import pl.coderslab.charity.model.dto.VerificationTokenDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.UserDetails;
import pl.coderslab.charity.model.entities.VerificationToken;
import pl.coderslab.charity.model.entities.embeddable.Role;
import pl.coderslab.charity.model.repositories.UserDetailsRepository;
import pl.coderslab.charity.model.repositories.UserRepository;
import pl.coderslab.charity.model.repositories.VerificationTokenRepository;
import pl.coderslab.charity.utils.AuthenticationFacade;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final VerificationTokenRepository verificationTokenRepository;
    private final AuthenticationFacade authenticationFacade;
    private final ModelMapper modelMapper;


    public UserService(UserRepository userRepository, UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher, VerificationTokenRepository verificationTokenRepository, AuthenticationFacade authenticationFacade, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
        this.verificationTokenRepository = verificationTokenRepository;
        this.authenticationFacade = authenticationFacade;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void addNewUser(UserRegistrationDto userRegistrationDto, WebRequest webRequest) {
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
        user.getRoles().add(roleUser);
        user.setUserDetails(savedUserDetails);
        userRepository.save(user);


        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, webRequest.getLocale(), webRequest.getContextPath()));
    }


    public void enableUser(Long userId) {
        User user = userRepository.getOne(userId);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Transactional
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken();
        myToken.setUser(user);
        myToken.setToken(token);

        verificationTokenRepository.save(myToken);
    }


    public boolean isUserWithEmailExists(String email) {
        return (userRepository.getByEmail(email) != null);
    }


    public VerificationTokenDto getVerificationTokenByToken(String token) {
        return tokenEntityToDto(verificationTokenRepository.getByToken(token));
    }

    private VerificationTokenDto tokenEntityToDto(VerificationToken token) {
        VerificationTokenDto tokenDto = modelMapper.map(token, VerificationTokenDto.class);
        tokenDto.setUserId(token.getUser().getId());

        return tokenDto;
    }


    public UserDetailsDto getLoggedUserProfileDetails() {
        String currentEmail = authenticationFacade.getName();
        UserDetailsDto userDetailsDto = modelMapper.map(userDetailsRepository.getByUserEmail(currentEmail), UserDetailsDto.class);
        userDetailsDto.setEmail(currentEmail);

        if (userDetailsDto.getPhoneNumber() == null) {
            userDetailsDto.setPhoneNumber("-");
        }

        return userDetailsDto;
    }

    @Transactional
    public void editProfileUpdate(UserDetailsDto userDetailsDto) {

        String currentEmail = authenticationFacade.getName();

        User user = userRepository.getByEmail(currentEmail);
        user.setEmail(userDetailsDto.getEmail());
        userRepository.save(user);

        UserDetails userDetails = user.getUserDetails();
        userDetails.setFirstName(userDetailsDto.getFirstName());
        userDetails.setLastName(userDetailsDto.getLastName());
        userDetails.setPhoneNumber(userDetailsDto.getPhoneNumber());
        userDetailsRepository.save(userDetails);

        authenticationFacade.setAuthenticationData(user.getEmail(), user.getPassword());
    }

    @Transactional
    public void editPassword(PasswordDto passwordDto) {

        if (passwordDto.getPassword().equals(passwordDto.getRepeatedPassword())) {
            User user = userRepository.getByEmail(authenticationFacade.getName());
            user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));
            userRepository.save(user);
        }

    }
}
