package pl.coderslab.charity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.model.dto.UserDto;
import pl.coderslab.charity.model.services.RegistrationService;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String prepareRegistration(Model model){
        model.addAttribute("newUser", new UserDto());

        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(UserDto newUser) {
        registrationService.addNewUser(newUser);

        return "redirect:/";
    }

}
