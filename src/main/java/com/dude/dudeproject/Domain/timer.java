package com.dude.dudeproject.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "set_timer_tbl")
@NoArgsConstructor
@AllArgsConstructor
public class timer {

    @Id
    private String user_id;
    private String time_set_home;
    private String time_set_away;
    private String spare;

}
