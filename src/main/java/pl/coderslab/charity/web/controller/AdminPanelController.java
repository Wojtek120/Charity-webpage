package pl.coderslab.charity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dto.InstitutionDto;
import pl.coderslab.charity.model.entities.Institution;
import pl.coderslab.charity.model.services.AdminService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {

    private final AdminService adminService;

    public AdminPanelController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/panel")
    public String adminPanel() {
        return "adminPanel";
    }

    @GetMapping("/institutions")
    public String prepareInstitutions(Model model) {
        model.addAttribute("institutions", adminService.getAllInstitutions());

        return "adminInstitutions";
    }

    @GetMapping("/institutions/edit/{id}")
    public String prepareInstitutionToEdit(Model model, @PathVariable Long id) {
        model.addAttribute("institution", adminService.getInstitutionById(id));

        return "editInstitution";
    }

    @PostMapping("/institutions/edit/{id}")
    public String saveEditedInstitution(@Valid InstitutionDto institution, BindingResult result) {

        if(result.hasErrors()) {
            return "editInstitution";
        }

        adminService.saveInstitution(institution);

        return "redirect:/admin/institutions";
    }

}
