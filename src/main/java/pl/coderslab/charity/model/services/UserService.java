package pl.coderslab.charity.model.services;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.charity.events.OnRegistrationCompleteEvent;
import pl.coderslab.charity.events.OnResetPasswordEvent;
import pl.coderslab.charity.model.dto.PasswordDto;
import pl.coderslab.charity.model.dto.UserDetailsDto;
import pl.coderslab.charity.model.dto.UserRegistrationDto;
import pl.coderslab.charity.model.dto.VerificationTokenDto;
import pl.coderslab.charity.model.entities.PasswordResetToken;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.UserDetails;
import pl.coderslab.charity.model.entities.VerificationToken;
import pl.coderslab.charity.model.entities.embeddable.Role;
import pl.coderslab.charity.model.repositories.PasswordResetTokenRepository;
import pl.coderslab.charity.model.repositories.UserDetailsRepository;
import pl.coderslab.charity.model.repositories.UserRepository;
import pl.coderslab.charity.model.repositories.VerificationTokenRepository;
import pl.coderslab.charity.utils.AuthenticationFacade;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final AuthenticationFacade authenticationFacade;
    private final ModelMapper modelMapper;


    public UserService(UserRepository userRepository, UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher, VerificationTokenRepository verificationTokenRepository, PasswordResetTokenRepository passwordResetTokenRepository, AuthenticationFacade authenticationFacade, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.authenticationFacade = authenticationFacade;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void addNewUserAndSendVerificationMail(UserRegistrationDto userRegistrationDto, WebRequest webRequest) {
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

        String token = UUID.randomUUID().toString();
        createVerificationToken(user, token);


        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, webRequest.getLocale(), webRequest.getContextPath(), token));
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

    @Transactional
    public void resendVerificationMail(String email, WebRequest webRequest) {
        User user = userRepository.getByEmail(email);
        VerificationToken token = verificationTokenRepository.getByUserEmail(email);
        Date date = new Date();
        long dayInMs = 24 * 60 * 60 * 1000;
        token.setExpiryDate(new Timestamp(date.getTime() + dayInMs));
        verificationTokenRepository.save(token);

        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, webRequest.getLocale(), webRequest.getContextPath(), token.getToken()));
    }

    @Transactional
    public void createResetPasswordTokenAndSendEmail(String email, WebRequest webRequest){

        String token;
        User user = userRepository.getByEmail(email);
        PasswordResetToken resetToken = passwordResetTokenRepository.getByUserEmail(email);

        if(resetToken == null) {
            resetToken = new PasswordResetToken();
            token = UUID.randomUUID().toString();
            resetToken.setToken(token);
            resetToken.setUser(user);
        } else  {
            resetToken.calculateExpiryDate();
            token = resetToken.getToken();
        }
        passwordResetTokenRepository.save(resetToken);

        applicationEventPublisher.publishEvent(new OnResetPasswordEvent(user, webRequest.getLocale(), webRequest.getContextPath(), token));
    }

    public boolean isUserEnabled(String email) {
        return userRepository.getByEmail(email).getEnabled();
    }

    public boolean isUserBanned(String email) {
        return userRepository.getByEmail(email).getBanned();
    }

    public boolean validateResetPasswordToken(String token, Long userId) {
        PasswordResetToken resetToken = passwordResetTokenRepository.getByToken(token);
        Calendar calendar = Calendar.getInstance();

        if ((resetToken != null)
                && (resetToken.getUser().getId().equals(userId))
                && ((resetToken.getExpiryDate().getTime() - calendar.getTime().getTime()) >= 0)) {

            User user = resetToken.getUser();
            authenticationFacade.setUserPasswordResetOnly(user);

            return true;
        }

        return false;
    }


    @Transactional
    public void resetPassword(PasswordDto passwordDto, Long userId) {

        if (passwordDto.getPassword().equals(passwordDto.getRepeatedPassword())) {
            User user = userRepository.getOne(userId);
            String encodedPassword = passwordEncoder.encode(passwordDto.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }

    }
}
