package com.dude.dudeproject.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Getter
@Setter
@Entity
public class service {
    @Id
    private String test;
}
