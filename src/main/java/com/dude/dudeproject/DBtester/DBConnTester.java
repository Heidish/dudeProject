package com.dude.dudeproject.DBtester;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Getter
@Setter
@Entity
public class DBConnTester {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_no;
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_mobile;
    private String user_address_gu;
    private String user_address_dong;
    private String user_address_street;
    private String user_address_city;


}
