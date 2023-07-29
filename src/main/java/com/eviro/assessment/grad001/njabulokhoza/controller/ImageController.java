package com.eviro.assessment.grad001.njabulokhoza.controller;

import com.eviro.assessment.grad001.njabulokhoza.model.AccountProfile;
import com.eviro.assessment.grad001.njabulokhoza.service.AccountProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {
    public final AccountProfileService accountProfileService;

    @Autowired
    public ImageController(AccountProfileService accountProfileService) {
        this.accountProfileService = accountProfileService;

    }

    @GetMapping("/{name}/{surname}")
    public FileSystemResource getHttpImageLink(@PathVariable String name,
                                               @PathVariable String surname) {

        AccountProfile accountProfile = accountProfileService
                .getAccountProfileByNameAndSurname(name, surname);

        if (accountProfile != null) {
            String imagePath = accountProfile.getHttpImageLink();
            return new FileSystemResource(imagePath);
        } else {
            return null;
        }
    }
    @PostMapping("/upload")
    public void uploadCSVFile(@RequestParam("file") MultipartFile csvFile) throws IOException {
        if (!csvFile.isEmpty()) {
            File file = File.createTempFile("upload", null);
            csvFile.transferTo(file);
            accountProfileService.parseCSV(file);
        } else {
            // Handle the case when no file is uploaded
        }
    }
}
