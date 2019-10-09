package pl.coderslab.charity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.model.dto.UserRegistrationDto;
import pl.coderslab.charity.model.services.UserService;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String prepareRegistration(Model model){
        model.addAttribute("newUser", new UserRegistrationDto());

        return "registration";
    }


    @PostMapping("/registration")
    public String registerNewUser(UserRegistrationDto newUser) {
        userService.addNewUser(newUser);

        return "redirect:/";
    }
}
