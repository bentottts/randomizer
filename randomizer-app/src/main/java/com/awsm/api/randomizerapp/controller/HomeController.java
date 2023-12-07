package com.awsm.api.randomizerapp.controller;

import com.awsm.api.randomizerapp.exception.EmptyRegistryException;
import com.awsm.api.randomizerapp.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    private final CandidateService candidateService;

    public HomeController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public String home() throws InterruptedException, EmptyRegistryException {
        return "dashboard";
    }

    @GetMapping("/roll")
    public String scanner(Model model){
        model.addAttribute("candidates", candidateService.getCandidates());
        return "roll";
    }

}
