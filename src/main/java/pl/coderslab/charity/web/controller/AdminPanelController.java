package pl.coderslab.charity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {

    @GetMapping("/panel")
    public String adminPanel() {
        return "adminPanel";
    }
}
