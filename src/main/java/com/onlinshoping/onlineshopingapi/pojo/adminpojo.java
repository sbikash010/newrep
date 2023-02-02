package com.onlinshoping.onlineshopingapi.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@RequiredArgsConstructor
@Getter
@Setter
@Data
public class adminpojo {
    @Id
    private Integer id;
    String name;
    String email;
    String address;
    String contactNumber;
}
