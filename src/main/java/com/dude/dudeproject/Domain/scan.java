package com.dude.dudeproject.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Data
@Entity
@Table(name="scan_tbl")
public class scan {

    @Id
    private String user_id;
    private Long case_number;
    private Long user_no_actor;
    private Long user_no_receiver;
    private Time scan_time;
    private String scan_location;
    private int contact_method;
    private Time contact_time;

}
