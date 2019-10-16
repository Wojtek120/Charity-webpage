package pl.coderslab.charity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dto.UserDetailsDto;
import pl.coderslab.charity.model.services.UsersAdminService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/users")
public class UsersAdminPanelController {

    private final UsersAdminService usersAdminService;

    public UsersAdminPanelController(UsersAdminService usersAdminService) {
        this.usersAdminService = usersAdminService;
    }

    @GetMapping
    public String prepareUsers(Model model) {
        model.addAttribute("users", usersAdminService.getAllUsers());

        return "adminUsers";
    }

    @GetMapping("/edit/{id}")
    public String prepareProfileDataToEdit(Model model, @PathVariable Long id) {
        model.addAttribute("user", usersAdminService.getUserById(id));

        return "editProfile";
    }

    @PostMapping("/edit/{id}")
    public String editProfile(@Valid UserDetailsDto userDetailsDto, BindingResult result) {

        if(result.hasErrors()) {
            return "editProfile";
        }

        usersAdminService.editProfileUpdate(userDetailsDto);

        return "redirect:/admin/users";
    }

}
