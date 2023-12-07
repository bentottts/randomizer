package com.awsm.api.randomizerapp.service.impl;

import com.awsm.api.randomizerapp.service.BackgroundImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class BackgroundImageServiceImpl implements BackgroundImageService {

    @Value("${app.background-image.file-name}")
    private String backgroundImageFilename;


    @Override
    public byte[] display() {
        byte[] data = null;
        try {
            data = Files.readAllBytes(Paths.get(this.backgroundImageFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
