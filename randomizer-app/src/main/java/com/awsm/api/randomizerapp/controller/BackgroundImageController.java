package com.awsm.api.randomizerapp.controller;

import com.awsm.api.randomizerapp.service.BackgroundImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BackgroundImageController {

    private final BackgroundImageService backgroundImageService;

    @Autowired
    public BackgroundImageController(BackgroundImageService backgroundImageService) {
        this.backgroundImageService = backgroundImageService;
    }
    @ResponseBody
    @GetMapping(value = "/home-background-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource displayHomeImage() {
        byte[] image = backgroundImageService.displayHomeImage();
        return new ByteArrayResource(image);
    }
    @ResponseBody
    @GetMapping(value = "/roll-background-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource displayRollImage() {
        byte[] image = backgroundImageService.displayRollingImage();
        return new ByteArrayResource(image);
    }
}
