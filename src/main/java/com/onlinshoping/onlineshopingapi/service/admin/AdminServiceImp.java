package com.onlinshoping.onlineshopingapi.service.admin;

import com.onlinshoping.onlineshopingapi.adminrepo.AdminRepo;
import com.onlinshoping.onlineshopingapi.model.admin.AdminDetails;
import com.onlinshoping.onlineshopingapi.pojo.adminpojo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {
    @Autowired
    private final   AdminRepo adminRepo;
    @Override
    public AdminDetails saveAdmin(AdminDetails adminDetails)  {
        return adminRepo.save(adminDetails);
    }

    @Override
    public List<AdminDetails> getadminlist() {
        return adminRepo.findAll();
    }

    @Override
    public AdminDetails getAdminById(Integer id)  {
        return adminRepo.findById(id).orElse(null);
    }

    @Override
    public AdminDetails getAdminByName(String name)  {
        return adminRepo.getAdminByName(name);
    }

    @Override
    public String deleteById(Integer id) {
        adminRepo.deleteById(id);
        return "admin is deleted whoskfsdjdfsjdsdfjldkksjfkle Admindklfjsk Id is bikash dkkfasfjnkkdeame shrestha "+id;
    }



    @Override
    public AdminDetails updateAdmin(adminpojo a) {
        AdminDetails existadmin=new AdminDetails();
        existadmin.setId(a.getId());
        existadmin.setName(a.getName());
        existadmin.setAddress(a.getAddress());
        existadmin.setEmail(a.getEmail());
        existadmin.setContactNumber(a.getContactNumber());
        adminRepo.save(existadmin);
        return existadmin;
    }
}
