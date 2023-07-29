package com.eviro.assessment.grad001.njabulokhoza.service;

import com.eviro.assessment.grad001.njabulokhoza.model.AccountProfile;
import com.eviro.assessment.grad001.njabulokhoza.repository.AccountProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class AccountProfileService implements FileParser {

    public final AccountProfileRepository accountProfileRepository;

    @Autowired
    public AccountProfileService(AccountProfileRepository accountProfileRepository) {
        this.accountProfileRepository = accountProfileRepository;
    }

    public AccountProfile saveAccountProfile(AccountProfile accountProfile) {
        return accountProfileRepository.save(accountProfile);
    }

    public void saveAccountProfiles(List<AccountProfile> accountProfiles) {
        accountProfileRepository.saveAll(accountProfiles);
    }

    public AccountProfile getAccountProfileByNameAndSurname(String name, String surname) {

        return accountProfileRepository
                .findByAccountHolderNameAndAccountHolderSurname(name, surname);
    }

    @Override
    public void parseCSV(File csvFile) {

        List<AccountProfile> accountProfiles = new ArrayList<AccountProfile>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {

                // Reading image data from CSV file
                String[] stringAccount = line.split(",");
                String name, surname, imageFormat, imageData;
                name = stringAccount[0];
                surname = stringAccount[1];
                imageFormat = stringAccount[2];
                imageData = stringAccount[3];

                String httpImageLink=createImageLink(convertCSVDataToImage(imageFormat,imageData)).toString();
                accountProfiles.add(new AccountProfile(name, surname, httpImageLink));
            }
            // Saving all Accounts in Array
            saveAccountProfiles(accountProfiles);
            accountProfiles.clear();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public File convertCSVDataToImage(String imageFormat, String base64ImageData) {


        String[] format = imageFormat.split("/");
        try {
            //Decoding the image
            byte[] imageData = Base64.getDecoder().decode(base64ImageData);
            // Creating an image file
            File imageFile = File.createTempFile(format[0], "." + format[1]);
            FileOutputStream fos = new FileOutputStream(imageFile);
            // Writing the image file
            fos.write(imageData);
            fos.close();
            return imageFile;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public URI createImageLink(File fileImage) {
        try {
            String publicDirectoryPath = "src/main/resources/static/images";
            Resource publicDirectoryResource = new FileSystemResource(publicDirectoryPath);
            File publicDirectory = publicDirectoryResource.getFile();

            if (!publicDirectory.exists()) {
                publicDirectory.mkdirs();
            }

            File movedImageFile = new File(publicDirectory, fileImage.getName());
            Path movedImagePath = Files.move(fileImage.toPath(), movedImageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            return movedImagePath.toUri();
        } catch (Exception e) {
            return null;
        }
    }





}
