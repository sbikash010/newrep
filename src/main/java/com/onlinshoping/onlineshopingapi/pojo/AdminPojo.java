package com.onlinshoping.onlineshopingapi.pojo;

import com.onlinshoping.onlineshopingapi.model.admin.AdminDetails;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminPojo {
        private Integer id;
        @NotBlank(message = "Admin is not blank")
        @NotNull(message = "Admin Name is not null!!!")
        private String name;
        @NotNull(message = "Email is not null!!!")
        @Pattern(regexp = "\"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@\" \n" +
                "  + \"[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$\"")
        private String email;

        @NotNull(message = "Address is required !!!")
        private String address;
        @NotNull(message = "contactNumber is required!!")
        private String contactNumber;


        public AdminPojo(AdminDetails adminDetails) {
                this.id=adminDetails.getId();
                this.name=adminDetails.getName();
                this.address=adminDetails.getAddress();
                this.email=adminDetails.getEmail();
                this.contactNumber=adminDetails.getContactNumber();
        }
}
