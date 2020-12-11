package com.dude.dudeproject.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class service {

    @Id
    private String test;

}
