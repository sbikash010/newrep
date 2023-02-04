package com.onlinshoping.onlineshopingapi.utils;

import com.onlinshoping.onlineshopingapi.pojo.AdminPojo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.UUID;


@Component
@Slf4j

public class FIleStorageUtils {
public String storeFile(AdminPojo adminPojo)
        {
        MultipartFile multipartFile=adminPojo.getMultipartFile();
        String fileDir=System.getProperty("user.dir")+ File.separator+"bikash";
//         String fileDir=new ClassPathResource("static").toString();


        File fileDirectory=new File(fileDir);
        if(!fileDirectory.exists()) {
        boolean mkdir= fileDirectory.mkdir();
        }else {
        log.info("file is already exist!!");
        }
        UUID uuid = UUID.randomUUID();
        String filepath=fileDir+File.separator+uuid+"_"+multipartFile.getOriginalFilename();
        fileDirectory=new File(filepath);
        try(FileOutputStream outputStream=new FileOutputStream(fileDirectory))
        {
        outputStream.write(multipartFile.getBytes());
        return filepath;
        }catch (Exception e)
        {
        e.printStackTrace();
        }
        return filepath;
        }
       public String base64Encoded(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] bytes = Files.readAllBytes(file.toPath());
        return  Base64.getEncoder().encodeToString(bytes);
        }
        public String base64Decoded(String filePath) throws IOException {
         Base64.Decoder decoder=Base64.getDecoder();
         byte[] decoderByte=decoder.decode(filePath);
         return  new String(decoderByte);

//            return  new String(Base64.getDecoder().decode(filePath));
        }
        }
