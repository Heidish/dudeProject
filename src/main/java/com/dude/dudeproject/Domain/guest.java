package com.dude.dudeproject.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
public class guest {

    @Id
    private Long id;

}
