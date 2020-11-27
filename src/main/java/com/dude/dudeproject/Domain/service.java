package com.dude.dudeproject.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class service {

    @Id
    private Long id;
    private Long user_no;
    private String target_qr_no;
    private Date qr_registration_date;

}
