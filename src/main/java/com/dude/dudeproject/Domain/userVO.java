package com.dude.dudeproject.Domain;

import lombok.Data;

@Data
public class userVO {
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
