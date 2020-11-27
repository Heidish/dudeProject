package com.dude.dudeproject.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;

@Data
@Entity
@Table(name="scan_tbl")
public class scan {

    @Id
    private Long id;
    private Long case_number;
    private Long user_no_actor;
    private Long user_no_receiver;
    private Timestamp scan_time;
    private DecimalFormat scan_location;
    private int contact_method;
    private Time contact_time;

}
