package pl.coderslab.charity.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import pl.coderslab.charity.model.dto.EmailDto;
import pl.coderslab.charity.model.dto.PasswordDto;
import pl.coderslab.charity.model.services.UserService;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class PasswordResetController {

    private final UserService userService;
    private final MessageSource messageSource;

    public PasswordResetController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping("/password/reset")
    public String prepareMailToWhichPasswordWillBeReseated(Model model) {
        model.addAttribute("email", new EmailDto());

        return "resendRegistrationToken";
    }

    @PostMapping("/password/reset")
    public String resetPassword(@Valid @ModelAttribute("email") EmailDto email, BindingResult result, Model model, WebRequest request) {
        Locale locale = request.getLocale();

        if(result.hasErrors()) {
            return "resendRegistrationToken";
        }

        if(!userService.isUserWithEmailExists(email.getEmail())) {
            String message = messageSource.getMessage("reset.password-message.user.doesnt.exist", null, locale);
            model.addAttribute("message", message);
            return "resendRegistrationToken";
        }

        userService.createResetPasswordTokenAndSendEmail(email.getEmail(), request);

        return "resetPasswordInfo";
    }

    @GetMapping("/password/reset/{token}/{userId}")
    public String prepareToResetPassword(@PathVariable String token, @PathVariable Long userId){

        if(userService.validateResetPasswordToken(token, userId)) {
            return "redirect:/password/reset/edit/" + userId;
        }

        return "resetPasswordError";

    }


    @GetMapping("/password/reset/edit/{userId}")
    public String prepareEditPassword(Model model, @PathVariable Long userId) {
        model.addAttribute("passwordDto", new PasswordDto());

        return "resetPassword";
    }

    @PostMapping("/password/reset/edit/{userId}")
    public String editPassword(@Valid PasswordDto passwordDto, BindingResult result, @PathVariable Long userId) {

        if(result.hasErrors()) {
            return "resetPassword";
        }

        if (!passwordDto.getPassword().equals(passwordDto.getRepeatedPassword())) {
            return "redirect:/password/reset/edit/" + userId + "?error=true";
        }

        userService.resetPassword(passwordDto, userId);

        SecurityContextHolder.clearContext();

        return "redirect:/";
    }

}
