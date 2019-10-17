package pl.coderslab.charity.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.dto.DonationDto;
import pl.coderslab.charity.model.services.DonationService;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/user")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/donation")
    public String prepareDonation(Model model){
        model.addAttribute("donationDto", new DonationDto());
        model.addAttribute("categories", donationService.getAllCategories());
        model.addAttribute("institutions", donationService.getAllInstitutions());

        return "donationForm";
    }

    @PostMapping("/donation")
    public String getDonation(@Valid DonationDto donationDto, BindingResult result) {
        log.debug(donationDto.toString());

        if (result.hasErrors()) {
            return "donationForm";
        }

        donationService.saveDonation(donationDto);

        return "redirect:/";
    }


}
