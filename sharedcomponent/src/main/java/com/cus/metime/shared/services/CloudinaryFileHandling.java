package com.cus.metime.shared.services;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class CloudinaryFileHandling {

    private Cloudinary cloudinary;

    public CloudinaryFileHandling(String cloudName, String apiKey, String apiSecret) {
        System.out.println("TEST 1231231232");
        cloudinary = new Cloudinary("cloudinary://"+apiKey+":"+apiSecret+"@"+cloudName);
    }



    public Map uploadIMage(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convFile);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        Map uploadResult = cloudinary.uploader().upload(convFile,ObjectUtils.asMap());

        return uploadResult;
    }

    public void deleteImage(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.asMap());
    }


}
