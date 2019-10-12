package pl.coderslab.charity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

        return "addEditInstitution";
    }

    @PostMapping("/institutions/edit/{id}")
    public String saveEditedInstitution(@Valid @ModelAttribute("institution") InstitutionDto institution, BindingResult result) {

        if(result.hasErrors()) {
            return "addEditInstitution";
        }

        adminService.saveInstitution(institution);

        return "redirect:/admin/institutions";
    }

    @GetMapping("/institutions/add")
    public String prepareInstitutionToAdd(Model model) {
        model.addAttribute("institution", new InstitutionDto());

        return "addEditInstitution";
    }

    @PostMapping("/institutions/add")
    public String addNewInstitution(@Valid InstitutionDto institution, BindingResult result) {

        if(result.hasErrors()) {
            return "addEditInstitution";
        }

        adminService.saveInstitution(institution);

        return "redirect:/admin/institutions";
    }

    @GetMapping("/institutions/delete/{id}")
    public String prepareToDelete(@PathVariable Long id, Model model){

        model.addAttribute("nameOfItemToDelete", adminService.getInstitutionById(id).getName());

        return "deleteConfirmation";
    }

    @PostMapping("/institutions/delete/{id}")
    public String deleteInstitution(@PathVariable Long id){

        adminService.deleteInstitution(adminService.getInstitutionById(id));

        return "redirect:/admin/institutions";
    }
}
