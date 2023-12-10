package com.awsm.api.randomizerapp.service.impl;

import com.awsm.api.randomizerapp.service.BackgroundImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class BackgroundImageServiceImpl implements BackgroundImageService {

    @Value("${app.background-image.home-file-name}")
    private String homeBackgroundImageFilename;

    @Value("${app.background-image.rolling-file-name}")
    private String rollingBackgroundImageFilename;


    @Override
    public byte[] displayHomeImage() {
        byte[] data = null;
        try {
            data = Files.readAllBytes(Paths.get(this.homeBackgroundImageFilename));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return data;
    }

    @Override
    public byte[] displayRollingImage() {
        byte[] data = null;
        try {
            data = Files.readAllBytes(Paths.get(this.rollingBackgroundImageFilename));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return data;
    }
}
