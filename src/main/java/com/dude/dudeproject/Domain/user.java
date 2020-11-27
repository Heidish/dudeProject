package com.dude.dudeproject.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Table(name="user_info_tbl")
@Entity
public class user {
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

    public String address(){
        String user_adress=this.user_address_street
                + this.user_address_dong
                + this.user_address_gu
                + this.user_address_dong;

        return user_adress;
    }
}
