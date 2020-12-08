package com.dude.dudeproject.Domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Table(name="service_tbl")
@Entity
public class service {

    @Id
    private String user_id;
    private String target_qr_no;
    private Timestamp qr_registration_date;

}
