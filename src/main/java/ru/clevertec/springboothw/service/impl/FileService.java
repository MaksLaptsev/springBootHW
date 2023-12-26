package ru.clevertec.springboothw.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Component
public class FileService {
    public String uploadFileAndDecodeToBase64(MultipartFile file) {

        if (file != null && !file.isEmpty()) {
            try {
                return Base64.getEncoder().encodeToString(file.getBytes());
            } catch (IOException e) {
                return getDefaultLogoFromResourcesBase64();
            }
        } else return getDefaultLogoFromResourcesBase64();
    }

    public String getDefaultLogoFromResourcesBase64() {
        try {
            return Base64.getEncoder().encodeToString(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("default.jpg")).readAllBytes());
        } catch (IOException e) {
            return "";
        }
    }
}
