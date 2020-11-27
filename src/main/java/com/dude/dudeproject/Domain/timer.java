package com.dude.dudeproject.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="set_timer")
public class timer {

    @Id
    private Long id;
    private Long user_no;
    private String time_set_home;
    private String time_set_away;

}
