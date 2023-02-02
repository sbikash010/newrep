package com.onlinshoping.onlineshopingapi.adminrepo;


import com.onlinshoping.onlineshopingapi.model.admin.AdminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.Optional;

public interface AdminRepo extends JpaRepository<AdminDetails,Integer> {

    @Query(name="select * from admin_details where name=?1",nativeQuery = true)
    AdminDetails getAdminByName(String name);
    @Query(name="select * from admin_details ad where id=?1",nativeQuery = true)
    AdminDetails getAdminById(Integer id);

}
