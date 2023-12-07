package com.awsm.api.randomizerapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/showCamera")
@Controller
public class CameraController {


    @GetMapping
    public String showCamera(){

        return "camera";
    }

}

