package com.dude.dudeproject.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "user_info_tbl")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @OneToOne
//    @JoinTable(name="service_tbl", joinColumns = @JoinColumn(name="user_no"))
    private Long user_no;
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_mobile;



}
