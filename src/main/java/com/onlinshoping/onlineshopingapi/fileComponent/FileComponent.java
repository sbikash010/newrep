package com.onlinshoping.onlineshopingapi.fileComponent;

import com.onlinshoping.onlineshopingapi.adminrepo.AdminRepo;
import com.onlinshoping.onlineshopingapi.pojo.AdminPojo;
import com.onlinshoping.onlineshopingapi.pojo.GlobleApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.UUID;

@Component
@Slf4j
public class FileComponent {
    public GlobleApiResponse storeFile(MultipartFile multipartFile)
    {
        String fileDir = System.getProperty("user.dir")+File.separator+"bikash";
        File directoryPath = new File(fileDir);
        if (!directoryPath.exists()) {
            boolean mkdirs = directoryPath.mkdirs();
        } else {
            log.info("File already exists!");
        }
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        assert extension != null;
        if (extension.equalsIgnoreCase("pdf")||extension.equalsIgnoreCase(("png")) ) {
            UUID uuid = UUID.randomUUID();

            String filePath = fileDir + File.separator + uuid + "-" + multipartFile.getOriginalFilename();
            File newFile = new File(filePath);
            try {
                multipartFile.transferTo(newFile);
            }catch (IOException e)
            {
                System.out.println("error");
            }

            return GlobleApiResponse.builder()
                    .status(true)
                    .massege("file is added successfully")
                    .data(filePath)
                    .build();
        } else {

            return GlobleApiResponse.builder()
                    .status(false)
                    .massege("Invalid extension")
                    .data(null)
                    .build();
        }
    }

    public String base64Encoded(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] bytes = Files.readAllBytes(file.toPath());
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
    }
}
