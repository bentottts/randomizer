package com.awsm.api.randomizerapp.controller;

import com.awsm.api.randomizerapp.exception.EmptyRegistryException;
import com.awsm.api.randomizerapp.model.Candidate;
import com.awsm.api.randomizerapp.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    private final CandidateService candidateService;

    public HomeController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public String home(Model model) throws InterruptedException, EmptyRegistryException {
        // landing page

//        List<String> strs = Arrays.asList("Toppings", "Al's","Tes","El","Em","Al","Ven","Son","Val","kev","Jek","Mar","Gina","Loy","Adam", "Bob", "Chris", "Dave", "Evan", "Fred", "Guy", "Hussai","Toppings", "Al's","Tes","El","Em","Al","Ven","Son","Val","kev","Jek","Mar","Gina","Loy","Adam", "Bob", "Chris", "Dave", "Evan", "Fred", "Guy", "Hussai","Toppings", "Al's","Tes","El","Em","Al","Ven","Son","Val","kev","Jek","Mar","Gina","Loy","Adam", "Bob", "Chris", "Dave", "Evan", "Fred", "Guy", "Hussai","Toppings", "Al's","Tes","El","Em","Al","Ven","Son","Val","kev","Jek","Mar","Gina","Loy","Adam", "Bob", "Chris", "Dave", "Evan", "Fred", "Guy", "Hussai");

        model.addAttribute("candidates", candidateService.getCandidates());
        return "index";

    }

    @GetMapping("/showWinner")
    public String showWinner(Model model) throws InterruptedException {

        //get winner
        Candidate candidate = candidateService.getWinner();
        log.info(candidate.toString());

        model.addAttribute("candidate", candidateService);
        return "index";
    }
}
