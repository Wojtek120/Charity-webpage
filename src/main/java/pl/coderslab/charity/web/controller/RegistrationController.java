package pl.coderslab.charity.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.charity.model.dto.UserRegistrationDto;
import pl.coderslab.charity.model.dto.VerificationTokenDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.services.UserService;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final MessageSource messageSource;

    public RegistrationController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping("/registration")
    public String prepareRegistration(Model model){
        model.addAttribute("newUser", new UserRegistrationDto());

        return "registration";
    }


    @PostMapping("/registration")
    public String registerNewUser(@Valid UserRegistrationDto newUser, BindingResult result, WebRequest webRequest) {

        //TODO add error messages in view
        if(result.hasErrors()) {
            return "registration";
        }

        userService.addNewUser(newUser, webRequest);

        return "authenticationInfo";
    }

    @GetMapping("/registration/confirm/{token}")
    public String confirmationOfRegistration(@PathVariable String token, Model model, WebRequest request){
        Locale locale = request.getLocale();
        VerificationTokenDto verificationToken = userService.getVerificationTokenByToken(token);

        if (verificationToken == null) {
            String message = messageSource.getMessage("authentication-message.invalid.token", null, locale);
            model.addAttribute("message", message);
            return "authenticationError";
        }

        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
            String message = messageSource.getMessage("authentication-message.token.expired", null, locale);
            model.addAttribute("message", message);
            return "authenticationError";
        }

        Long userId = verificationToken.getUserId();
        userService.enableUser(userId);

        return "redirect:/login";
    }
}
