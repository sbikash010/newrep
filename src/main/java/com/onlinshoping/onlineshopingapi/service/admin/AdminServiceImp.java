package com.onlinshoping.onlineshopingapi.service.admin;
import com.onlinshoping.onlineshopingapi.adminrepo.AdminRepo;
import com.onlinshoping.onlineshopingapi.model.admin.AdminDetails;
import com.onlinshoping.onlineshopingapi.pojo.AdminPojo;
import com.onlinshoping.onlineshopingapi.pojo.GlobleApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImp implements AdminService {

    private final   AdminRepo adminRepo;
    @Override
    public AdminPojo saveAdmin(AdminPojo adminPojo) throws Exception  {

        AdminDetails adminDetails=AdminDetails.builder()
                .id(adminPojo.getId())
                .address(adminPojo.getAddress())
                .email(adminPojo.getEmail())
                .name(adminPojo.getName())
                .contactNumber(adminPojo.getContactNumber())
                .build();
         return  adminRepo.save(adminDetails);

    }

    @Override
    public GlobleApiResponse deleteById(Integer id) {;
        AdminDetails adminDetails=adminRepo.findById(id).orElseThrow(()->new RuntimeException("Admin is not exist!!!"));
        adminRepo.delete(adminDetails);
        return  GlobleApiResponse.builder().status(true).massege("Admin delete successfully").build();
    }

    @Override
    public AdminPojo getAdminById(Integer id) {
        AdminDetails adminDetails=adminRepo.findById(id).orElseThrow(()->new RuntimeException("Admin is not recoded!!!"));

        return new AdminPojo(adminDetails);
    }

    @Override
    public AdminPojo updateByAdminId(AdminPojo adminPojo) {
         AdminDetails findadminDetails=adminRepo.findById(adminPojo.getId()).orElseThrow(()->new RuntimeException("admin is not found!!! "));

                  findadminDetails.setId(adminPojo.getId());
                  findadminDetails.setAddress(adminPojo.getAddress());
                  findadminDetails.setContactNumber(adminPojo.getContactNumber());
                  findadminDetails.setEmail(adminPojo.getEmail());
                  findadminDetails.setName(adminPojo.getName());
//                          builder().id(adminPojo.getId())
//                                  .address(adminPojo.getAddress())
//                                  .email(adminPojo.getEmail())
//                                  .contactNumber(adminPojo.getContactNumber())
//                                  .name(adminPojo.getName())
//                                  .build();
            AdminDetails adminDetails=adminRepo.save(findadminDetails);

        return new AdminPojo(adminDetails);
    }

    @Override
    public ResponseEntity uploadFileByAdmin(MultipartFile multipartFile) {
      String fileDir=System.getProperty("user.home")+ File.separator+"Desktop"+File.separator+"onlineshop";
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
            return ResponseEntity.ok(new GlobleApiResponse(true,"file successfully upload",null));
        }catch (Exception e)
       {
           return ResponseEntity.badRequest().body(new GlobleApiResponse(false,"file not successfully uploaded",null));
       }

    }
}
