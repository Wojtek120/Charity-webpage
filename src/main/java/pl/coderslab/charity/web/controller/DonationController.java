package pl.coderslab.charity.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.model.dto.CategoryDto;
import pl.coderslab.charity.model.dto.DonationDto;
import pl.coderslab.charity.model.services.DonationFormService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class DonationController {

    private final DonationFormService donationFormService;

    public DonationController(DonationFormService donationFormService) {
        this.donationFormService = donationFormService;
    }

    @GetMapping("/donation")
    public String prepareDonation(Model model){
        model.addAttribute("donationDto", new DonationDto());
        model.addAttribute("categories", donationFormService.getAllCategories());
        model.addAttribute("institutions", donationFormService.getAllInstitutions());

        return "donationForm";
    }

    @PostMapping("/donation")
    public String getDonation(@Valid DonationDto donationDto, BindingResult result) {
        log.debug(donationDto.toString());

        if (result.hasErrors()) {
            return "donationForm";
        }

        donationFormService.saveDonation(donationDto);

        return "redirect:/";
    }


}
