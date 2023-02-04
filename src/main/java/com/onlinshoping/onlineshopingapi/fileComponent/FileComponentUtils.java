package com.onlinshoping.onlineshopingapi.fileComponent;

import com.onlinshoping.onlineshopingapi.pojo.AdminPojo;
import com.onlinshoping.onlineshopingapi.pojo.GlobleApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Component
@Slf4j
public class FileComponentUtils {
    public String storeFile(AdminPojo adminPojo)
    {
        MultipartFile multipartFile=adminPojo.getMultipartFile();
        String fileDir=System.getProperty("user.dir")+ File.separator+"bikash";
        File fileDirectory=new File(fileDir);
        if(!fileDirectory.exists()) {
            boolean mkdir= fileDirectory.mkdir();
        }else {
            log.info("file is already exist!!");
        }
        String filepath=fileDir+File.separator+multipartFile.getOriginalFilename();
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
        return "data:pdf/png/jpg;base64," + Base64.getEncoder().encodeToString(bytes);
    }
}
