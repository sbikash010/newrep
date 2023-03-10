package com.onlinshoping.onlineshopingapi.service.admin;

import com.onlinshoping.onlineshopingapi.model.admin.AdminDetails;
import com.onlinshoping.onlineshopingapi.pojo.AdminPojo;
import com.onlinshoping.onlineshopingapi.pojo.GlobleApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminService {
     AdminPojo saveAdmin(AdminPojo adminPojo) throws Exception;
     List<AdminPojo> getAllDetails();
    List<AdminPojo> getAllDetailsWithSort(int pageNumber,int pageSize);

      AdminPojo getAdminById(Integer id);
      GlobleApiResponse deleteById(Integer id);
    ResponseEntity uploadFileByAdmin(AdminPojo adminPojo);
//      AdminPojo updateByAdminId(AdminPojo adminPojo);
//      ResponseEntity uploadFileByAdmin(MultipartFile multipartFile);

}
