package pl.coderslab.charity.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.charity.model.dto.EmailDto;
import pl.coderslab.charity.model.dto.UserRegistrationDto;
import pl.coderslab.charity.model.dto.VerificationTokenDto;
import pl.coderslab.charity.model.services.UserService;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;
    private final MessageSource messageSource;

    public RegistrationController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public String prepareRegistration(Model model){
        model.addAttribute("newUser", new UserRegistrationDto());

        return "registration";
    }


    @PostMapping
    public String registerNewUser(@Valid @ModelAttribute("newUser") UserRegistrationDto newUser, BindingResult result, WebRequest webRequest, Model model) {

        if(userService.isUserWithEmailExists(newUser.getEmail())) {
            model.addAttribute("userExist", true);
            return "registration";
        }

        if(result.hasErrors()) {
            return "registration";
        }

        if (!newUser.getPassword().equals(newUser.getRepeatedPassword())) {
            model.addAttribute("wrongPass", true);
            return "registration";
        }

        userService.addNewUserAndSendVerificationMail(newUser, webRequest);

        return "authenticationInfo";
    }

    @GetMapping("/confirm/{token}")
    public String confirmationOfRegistration(@PathVariable String token, Model model, WebRequest request){
        Locale locale = request.getLocale();
        VerificationTokenDto verificationToken = userService.getVerificationTokenByToken(token);

        if (verificationToken == null) {
            String message = messageSource.getMessage("authentication-message.invalid.token", null, locale);
            model.addAttribute("message", message);
            return "authenticationError";
        }

        if (!userService.isVerificationTokenValid(verificationToken)) {
            String message = messageSource.getMessage("authentication-message.token.expired", null, locale);
            model.addAttribute("message", message);
            return "authenticationError";
        }

        Long userId = verificationToken.getUserId();
        userService.enableUser(userId);

        return "redirect:/login";
    }

    @GetMapping("/resend")
    public String prepareToResendRegistrationToken(Model model) {
        model.addAttribute("email", new EmailDto());

        return "resendRegistrationToken";
    }

    @PostMapping("/resend")
    public String resendRegistrationToken(@Valid @ModelAttribute("email") EmailDto email, BindingResult result, Model model, WebRequest request) {
        Locale locale = request.getLocale();

        if(result.hasErrors()) {
            return "resendRegistrationToken";
        }

        if(!userService.isUserWithEmailExists(email.getEmail())) {
            String message = messageSource.getMessage("resend-message.user.doesnt.exist", null, locale);
            model.addAttribute("message", message);
            return "resendRegistrationToken";
        }

        if(userService.isUserEnabled(email.getEmail())) {
            String message = messageSource.getMessage("resend-message.user.already.active", null, locale);
            model.addAttribute("message", message);
            return "resendRegistrationToken";
        }

        if(userService.isUserBanned(email.getEmail())) {
            String message = messageSource.getMessage("resend-message.user.banned", null, locale);
            model.addAttribute("message", message);
            return "resendRegistrationToken";
        }

        userService.resendVerificationMail(email.getEmail(), request);

        return "authenticationInfo";
    }

}
