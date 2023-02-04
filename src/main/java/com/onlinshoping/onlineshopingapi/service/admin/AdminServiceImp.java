package com.onlinshoping.onlineshopingapi.service.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinshoping.onlineshopingapi.adminrepo.AdminRepo;
import com.onlinshoping.onlineshopingapi.model.admin.AdminDetails;
import com.onlinshoping.onlineshopingapi.pojo.AdminPojo;
import com.onlinshoping.onlineshopingapi.pojo.GlobleApiResponse;
import com.onlinshoping.onlineshopingapi.utils.FIleStorageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImp implements AdminService {

    private final   AdminRepo adminRepo;
    private final FIleStorageUtils fIleStorageUtils;
    @Override
    public AdminPojo saveAdmin(AdminPojo adminPojo) throws Exception  {

        AdminDetails adminDetails;
        if(adminPojo.getId()!=null)
            adminDetails = adminRepo.findById(adminPojo.getId()).orElseThrow(() ->{throw new RuntimeException("Admin not found");});
        else
            adminDetails = new AdminDetails();


        String filepath=fIleStorageUtils.storeFile(adminPojo);


        adminDetails.setId(adminPojo.getId());
        adminDetails.setName(adminPojo.getName());
        adminDetails.setEmail(adminPojo.getEmail());
        adminDetails.setFilepath(fIleStorageUtils.base64Encoded(filepath));
        adminDetails.setContactNumber(adminPojo.getContactNumber());
        adminDetails.setAddress(adminPojo.getAddress());
        adminRepo.save(adminDetails);
        return adminPojo;


    }

    @Override
    public GlobleApiResponse deleteById(Integer id) {
        AdminDetails adminDetails=adminRepo.findById(id).orElseThrow(()->new RuntimeException("Admin is not exist!!!"));
        adminRepo.delete(adminDetails);
        return  GlobleApiResponse.builder().status(true).massege("Admin delete successfully").build();
    }
    @Override
    public List<AdminPojo> getAllDetails() {

        return adminRepo.findAll()
                .stream().map(
                        adminDetails ->{
                            try {
                                return AdminPojo.builder()
                                        .id(adminDetails.getId())
                                        .name(adminDetails.getName())
                                        .address(adminDetails.getAddress())
                                        .email(adminDetails.getEmail())
                                        .contactNumber(adminDetails.getContactNumber())
                                        .fileLocation(fIleStorageUtils.base64Decoded(adminDetails.getFilepath()))
                                        .build();
                            }catch (IOException e)
                            {
                                e.printStackTrace();
                                return null;
                            }

                        })
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminPojo> getAllDetailsWithSort(int pageNumber,int pageSize ) {
        return adminRepo.findAll(PageRequest.of(pageNumber,pageSize)).stream().map(
                adminDetails ->{
                    try {
                        return AdminPojo.builder()
                                .id(adminDetails.getId())
                                .name(adminDetails.getName())
                                .address(adminDetails.getAddress())
                                .email(adminDetails.getEmail())
                                .contactNumber(adminDetails.getContactNumber())
                                .fileLocation(fIleStorageUtils.base64Decoded(adminDetails.getFilepath()))
                                .build();
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                        return null;
                    }

                })
                .collect(Collectors.toList());
    }

    @Override
    public AdminPojo getAdminById(Integer id) {
        AdminDetails adminDetails=adminRepo.findById(id).orElseThrow(()->new RuntimeException("Admin is not recoded!!!"));
        try {
            String path=fIleStorageUtils.base64Decoded(adminDetails.getFilepath());
            return AdminPojo.builder()
                    .name(adminDetails.getName())
                    .address(adminDetails.getAddress())
                    .email(adminDetails.getEmail())
                    .contactNumber(adminDetails.getContactNumber())
                    .fileLocation(path)
                    .build();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
       return null;

    }

//    @Override
//    public AdminPojo updateByAdminId(AdminPojo adminPojo) {
//         AdminDetails findadminDetails=adminRepo.findById(adminPojo.getId()).orElseThrow(()->new RuntimeException("admin is not found!!! "));
//
//                  findadminDetails.setId(adminPojo.getId());
//                  findadminDetails.setAddress(adminPojo.getAddress());
//                  findadminDetails.setContactNumber(adminPojo.getContactNumber());
//                  findadminDetails.setEmail(adminPojo.getEmail());
//                  findadminDetails.setName(adminPojo.getName());
//            AdminDetails adminDetails=adminRepo.save(findadminDetails);
//
//        return new AdminPojo(adminDetails);
//    }

//    @Override
//    public ResponseEntity uploadFileByAdmin(MultipartFile multipartFile) {
//      String fileDir=System.getProperty("user.dir")+File.separator+"bikash";
//      File fileDirectory=new File(fileDir);
//      if(!fileDirectory.exists()) {
//            boolean mkdir= fileDirectory.mkdir();
//      }else {
//          log.info("file is already exist!!");
//      }
//      String filepath=fileDir+File.separator+multipartFile.getOriginalFilename();
//       fileDirectory=new File(filepath);
//       try(FileOutputStream outputStream=new FileOutputStream(fileDirectory))
//        {
//            outputStream.write(multipartFile.getBytes());
//            return ResponseEntity.ok(new GlobleApiResponse(true,"file successfully upload",filepath));
//        }catch (Exception e)
//       {
//           return ResponseEntity.badRequest().body(new GlobleApiResponse(false,"file not successfully uploaded",null));
//       }
//
//    }
@Override
public ResponseEntity uploadFileByAdmin(AdminPojo adminPojo) {
        MultipartFile multipartFile=adminPojo.getMultipartFile();
    String fileDir=System.getProperty("user.dir")+File.separator+"bikash";
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
        return ResponseEntity.ok(new GlobleApiResponse(true,"file successfully upload",filepath));
    }catch (Exception e)
    {
        return ResponseEntity.badRequest().body(new GlobleApiResponse(false,"file not successfully uploaded",null));
    }

}
}
