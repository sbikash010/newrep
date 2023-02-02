package com.onlinshoping.onlineshopingapi.model.admin;

import lombok.*;

import javax.persistence.*;

@Entity(name="admin_details")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "admin_token_gen")
            @SequenceGenerator(name = "admin_token_gen",sequenceName = "admin_token_seq")
    private Integer id;
    String name;
    String email;
    String address;
    String contactNumber;
}
