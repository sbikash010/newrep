//package com.onlinshoping.onlineshopingapi.utils;
//
//import com.onlinshoping.onlineshopingapi.pojo.AdminPojo;
//import com.onlinshoping.onlineshopingapi.pojo.GlobleApiResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.FilenameUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.Base64;
//import java.util.UUID;
//
//@Component
//@Slf4j
//
//public class FIleStorageUtils {
//    public GlobleApiResponse storeFile(AdminPojo adminPojo) {
//
//        MultipartFile multipartFile =adminPojo.getMultipartFile();
//
//
//        if (adminPojo.getId() == null && multipartFile.isEmpty()) {
//            return GlobleApiResponse.builder().status(true).massege("image is required").build();
//        }
//
//        String directoryPath = System.getProperty("user.dir") + File.separator + "image";
//        File directoryFile = new File(directoryPath);
//        if (!directoryFile.exists()) {
//            directoryFile.mkdirs();
//        } else {
//            log.info("Directory Exists");
//        }
//
//        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
//
//
//        if (extension.equalsIgnoreCase("pdf")||extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("gif")) {
//
//            UUID uuid = UUID.randomUUID();
//            String filePath = directoryPath + File.separator + uuid + "_" + multipartFile.getOriginalFilename();
//            File fileToStore = new File(filePath);
//
//            try {
//                multipartFile.transferTo(fileToStore);
//                return GlobleApiResponse.builder().status(true).massege("file is upload successfully").data(filePath).build();
//            }
//
//            catch (IOException exception) {
//                return GlobleApiResponse.builder().status(false).massege("Could not read file").build();
//            }
//        }
//        else {
//            return GlobleApiResponse.builder().status(false).massege("Only pdg,jpg, jpeg,gif and png format are supported").build();
//        }
//    }
//
//
//    public String returnFileAsBase64(String filePath) {
//        File file = new File(filePath);
//        try {
//            byte[] bytes = Files.readAllBytes(file.toPath());
//            String base64EncodedImage = "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
//            return base64EncodedImage;
//        } catch (IOException exception) {
//            log.error(exception.getMessage());
//            return null;
//        }
//    }
//}
