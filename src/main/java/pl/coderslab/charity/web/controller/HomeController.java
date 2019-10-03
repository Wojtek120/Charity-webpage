package pl.coderslab.charity.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.services.HomeService;

@Slf4j
@Controller
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institutions", homeService.getAllInstitutions());
        model.addAttribute("quantitySum", homeService.getQuantityOfDonatedBags());
        model.addAttribute("institutionsNumber", homeService.getNumberOfDonatedInstitutions());

        return "index";
    }
}
