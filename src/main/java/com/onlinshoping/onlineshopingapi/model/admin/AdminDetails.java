package com.onlinshoping.onlineshopingapi.model.admin;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name="admin_details")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "admin_details_seq_gen")
    @SequenceGenerator(name = "admin_details_seq_gen",sequenceName = "admin_seq",allocationSize = 1)
    private Integer id;
    @NotBlank(message = "Admin is not blank")
    @NotNull(message = "Admin Name is not null!!!")
    private String name;
    @NotNull(message = "Admin Name is not null!!!")
    @Pattern(regexp = "\"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@\" \n" +
            "  + \"[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$\"")
   private String email;
    @NotNull(message = "Address is required !!!")
    private String address;
    @Column(name = "contaact_number")
    @Size(min = 10,max = 10)

    private String contactNumber;

}
