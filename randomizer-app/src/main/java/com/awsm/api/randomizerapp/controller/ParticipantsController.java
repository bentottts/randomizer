package com.awsm.api.randomizerapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/participants")
public class ParticipantsController {

    @GetMapping("/all")
    public String getAll(){
        return "participants";
    }
}
