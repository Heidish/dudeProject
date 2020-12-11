package com.dude.dudeproject.Domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Data
@Table(name="service_tbl")
@Entity
public class service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id;

    private String target_qr_no;
    private String qr_image;


}
