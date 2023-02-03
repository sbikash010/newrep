package com.onlinshoping.onlineshopingapi.service.admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinshoping.onlineshopingapi.adminrepo.AdminRepo;
import com.onlinshoping.onlineshopingapi.fileComponent.FileComponent;
import com.onlinshoping.onlineshopingapi.model.admin.AdminDetails;
import com.onlinshoping.onlineshopingapi.pojo.AdminPojo;
import com.onlinshoping.onlineshopingapi.pojo.GlobleApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImp implements AdminService {

    private final   AdminRepo adminRepo;

    private FileComponent fileComponent;
    @Override
    public AdminPojo saveAdmin(AdminPojo adminPojo) throws Exception  {

        AdminDetails adminDetails;
        if(adminPojo.getId()!=null)
            adminDetails = adminRepo.findById(adminPojo.getId()).orElseThrow(() ->{throw new RuntimeException("Admin not found");});
        else
            adminDetails = new AdminDetails();

       try {
           GlobleApiResponse globleApiResponse = fileComponent.storeFile(adminPojo.getMultipartFile());
           adminDetails.setId(adminPojo.getId());
           adminDetails.setName(adminPojo.getName());
           adminDetails.setEmail(adminPojo.getEmail());
           adminDetails.setFilepath(globleApiResponse.getData().toString());
           adminDetails.setContactNumber(adminPojo.getContactNumber());
           adminDetails.setAddress(adminPojo.getAddress());
           adminRepo.save(adminDetails);
       }catch (NullPointerException e)
       {

       }



//        adminDetails.setId(adminPojo.getId());
//        adminDetails.setName(adminPojo.getName());
//        adminDetails.setEmail(adminPojo.getEmail());
//        adminDetails.setContactNumber(adminPojo.getContactNumber());
//        adminDetails.setAddress(adminPojo.getAddress());
//        adminRepo.save(adminDetails);
        return adminPojo;





//            if(adminPojo.getId()!=null)
//        {
//           AdminDetails adminDetailsById=adminRepo.findById(adminPojo.getId()).orElseThrow(()->new RuntimeException("Admin detail is not exist!!!"));
//            adminDetailsById.setId(adminPojo.getId());
//            adminDetailsById.setName(adminPojo.getName());
//            adminDetailsById.setEmail(adminPojo.getEmail());
//            adminDetailsById.setContactNumber(adminPojo.getContactNumber());
//            adminDetailsById.setAddress(adminPojo.getAddress());
//           adminRepo.save(adminDetailsById);
//        }else {
//            AdminDetails adminDetails = AdminDetails.builder()
//                    .address(adminPojo.getAddress())
//                    .email(adminPojo.getEmail())
//                    .name(adminPojo.getName())
//                    .contactNumber(adminPojo.getContactNumber())
//                    .build();
//            adminDetails=adminRepo.save(adminDetails);
//            return new AdminPojo(adminDetails);
//        }
//        return adminPojo;

//        AdminDetails adminDetailsById=adminRepo.findById(adminPojo.getId()).orElseThrow(()->new RuntimeException("Admin is not exist!!!"));
//        if(adminDetailsById.getId()!=adminPojo.getId()) {
//            AdminDetails adminDetails = AdminDetails.builder()
//                    .address(adminPojo.getAddress())
//                    .email(adminPojo.getEmail())
//                    .name(adminPojo.getName())
//                    .contactNumber(adminPojo.getContactNumber())
//                    .build();
//            adminRepo.save(adminDetails);
//        }
//        adminDetailsById.setId(adminPojo.getId());
//        adminDetailsById.setName(adminPojo.getName());
//        adminDetailsById.setEmail(adminPojo.getEmail());
//        adminDetailsById.setContactNumber(adminPojo.getContactNumber());
//        adminDetailsById.setAddress(adminPojo.getAddress());
//        adminRepo.save(adminDetailsById);
//        return adminPojo;
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

//        return new AdminPojo(adminDetails);
        ObjectMapper objectMapper=new ObjectMapper();
        return  objectMapper.convertValue(adminDetails,AdminPojo.class);

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
//            return ResponseEntity.ok(new GlobleApiResponse(true,"file successfully upload",null));
//        }catch (Exception e)
//       {
//           return ResponseEntity.badRequest().body(new GlobleApiResponse(false,"file not successfully uploaded",null));
//       }
//
//    }
}
