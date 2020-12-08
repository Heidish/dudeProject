package com.dude.dudeproject.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
<<<<<<< HEAD
=======
import lombok.NoArgsConstructor;
>>>>>>> 0fef08fc8a5e7ce7771e6732264326d13562dd62

import javax.persistence.*;

@Data
@Table(name = "user_info_tbl")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_no;


    private String user_id;

    private String user_pw;
    private String user_name;
    private String user_mobile;


}
