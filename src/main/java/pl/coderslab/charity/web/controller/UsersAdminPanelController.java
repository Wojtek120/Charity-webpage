package pl.coderslab.charity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.services.UsersAdminService;

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
}
