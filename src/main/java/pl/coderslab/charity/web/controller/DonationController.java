package pl.coderslab.charity.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.model.dto.DonationDto;
import pl.coderslab.charity.model.services.CategoryService;
import pl.coderslab.charity.model.services.DonationService;
import pl.coderslab.charity.model.services.InstitutionService;

import javax.validation.Valid;

@Controller
@Slf4j
public class DonationController {

    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;

    public DonationController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @GetMapping("/donation")
    public String prepareDonation(Model model){

        model.addAttribute("donationDto", new DonationDto());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("institutions", institutionService.getAll());

        return "donationForm";
    }

    @PostMapping("/donation")
    public String getDonation(@Valid DonationDto donationDto, BindingResult result) {
        log.debug(donationDto.toString());

        if (result.hasErrors()) {
            return "donationForm";
        }

        donationService.save(donationDto);

        return "redirect:/";
    }


}
