package pl.coderslab.charity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dto.UserDetailsDto;
import pl.coderslab.charity.model.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserProfileController {

    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String setUserProfileView(Model model) {

        model.addAttribute("user", userService.getLoggedUserProfileDetails());

        return "userProfile";
    }

    @GetMapping("/edit/profile")
    public String prepareProfileDataToEdit(Model model) {
        model.addAttribute("user", userService.getLoggedUserProfileDetails());

        return "editProfile";
    }

    @PostMapping("/edit/profile")
    public String editProfile(@Valid UserDetailsDto userDetailsDto, BindingResult result) {

        if(result.hasErrors()) {
            return "editProfile";
        }

        userService.editProfileUpdate(userDetailsDto);

        return "redirect:/user/profile";
    }
}
