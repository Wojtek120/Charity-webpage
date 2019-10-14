package pl.coderslab.charity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.dto.InstitutionDto;
import pl.coderslab.charity.model.services.InstitutionsAdminService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/institutions")
public class InstitutionsAdminPanelController {

    private final InstitutionsAdminService institutionsAdminService;

    public InstitutionsAdminPanelController(InstitutionsAdminService institutionsAdminService) {
        this.institutionsAdminService = institutionsAdminService;
    }

    @GetMapping
    public String prepareInstitutions(Model model) {
        model.addAttribute("institutions", institutionsAdminService.getAllInstitutions());

        return "adminInstitutions";
    }

    @GetMapping("/edit/{id}")
    public String prepareInstitutionToEdit(Model model, @PathVariable Long id) {
        model.addAttribute("institution", institutionsAdminService.getInstitutionById(id));

        return "addEditInstitution";
    }

    @PostMapping("/edit/{id}")
    public String saveEditedInstitution(@Valid @ModelAttribute("institution") InstitutionDto institution, BindingResult result) {

        if(result.hasErrors()) {
            return "addEditInstitution";
        }

        institutionsAdminService.saveInstitution(institution);

        return "redirect:/admin/institutions";
    }

    @GetMapping("/add")
    public String prepareInstitutionToAdd(Model model) {
        model.addAttribute("institution", new InstitutionDto());

        return "addEditInstitution";
    }

    @PostMapping("/add")
    public String addNewInstitution(@Valid InstitutionDto institution, BindingResult result) {

        if(result.hasErrors()) {
            return "addEditInstitution";
        }

        institutionsAdminService.saveInstitution(institution);

        return "redirect:/admin/institutions";
    }

    @GetMapping("/delete/{id}")
    public String prepareToDelete(@PathVariable Long id, Model model){

        model.addAttribute("nameOfItemToDelete", institutionsAdminService.getInstitutionById(id).getName());

        return "deleteConfirmation";
    }

    @PostMapping("/delete/{id}")
    public String deleteInstitution(@PathVariable Long id){

        institutionsAdminService.deleteInstitution(institutionsAdminService.getInstitutionById(id));

        return "redirect:/admin/institutions";
    }
}
