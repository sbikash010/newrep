package com.onlinshoping.onlineshopingapi.controller.adminController;
import com.onlinshoping.onlineshopingapi.pojo.AdminPojo;
import com.onlinshoping.onlineshopingapi.pojo.GlobleApiResponse;
import com.onlinshoping.onlineshopingapi.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1")
public class AdminController {
    private final AdminService adminService;


    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GlobleApiResponse> saveAdminDetail(@ModelAttribute @Valid AdminPojo adminPojo,BindingResult bindingResult) throws Exception {

        if(bindingResult.hasErrors()) {
            String errror=bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(new GlobleApiResponse(false,errror,null));
        }
        AdminPojo adminPojo1 = adminService.saveAdmin(adminPojo);
        return ResponseEntity.ok(new GlobleApiResponse(true, "Admin is add successfully", adminPojo1));

    }
    @GetMapping()
    public ResponseEntity<List<AdminPojo>> getAllByAdminDetails()
    {
        List<AdminPojo> allDetailsAdmin=adminService.getAllDetails();
        return new ResponseEntity<List<AdminPojo>>(allDetailsAdmin,HttpStatus.OK);
    }
    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<List<AdminPojo>> getAllByAdminDetailsWithSort(@PathVariable int pageNumber,@PathVariable int pageSize)
    {
        List<AdminPojo> allDetailsAdmin=adminService.getAllDetailsWithSort(pageNumber,pageSize);
        return new ResponseEntity<List<AdminPojo>>(allDetailsAdmin,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobleApiResponse> getAdminDetailById(@PathVariable("id") Integer id)throws Exception
    {
        return ResponseEntity.ok(new GlobleApiResponse(true,"Admin fetch successfully",adminService.getAdminById(id)));
    }
    @DeleteMapping("/{adminid}")
    public ResponseEntity deleteAdminById(@PathVariable("adminid") Integer id )
    {
      return new ResponseEntity(adminService.deleteById(id), HttpStatus.OK);
    }



//    @PutMapping()
//    public ResponseEntity<GlobleApiResponse> updateAdminDetaitById(@RequestBody  AdminPojo adminPojo)
//    {
//        return ResponseEntity.ok(new GlobleApiResponse(true,"update is Successfully",adminService.updateByAdminId(adminPojo)));
//
//    }
//
//@PostMapping("/uploadFile")
//public ResponseEntity<GlobleApiResponse> fileUpload(MultipartFile multipartFile) throws Exception
//{
//    if(multipartFile.isEmpty())
//    {
//        return ResponseEntity.badRequest().body(new GlobleApiResponse(false,"file is not upload",null));
//    }
//    return ResponseEntity.ok(new GlobleApiResponse(true,"file is upload successfully",adminService.uploadFileByAdmin(multipartFile)));
//}

    @PostMapping("/uploadFile")
    public ResponseEntity<GlobleApiResponse> fileUpload( AdminPojo adminPojo) throws Exception
    {
        return adminService.uploadFileByAdmin(adminPojo);
//        if(multipartFile.isEmpty())
//        {
//            return ResponseEntity.badRequest().body(new GlobleApiResponse(false,"file is not upload",null));
//        }
//        return ResponseEntity.ok(new GlobleApiResponse(true,"file is upload successfully",adminService.uploadFileByAdmin(multipartFile)));
    }

}
