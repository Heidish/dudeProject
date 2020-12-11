package com.dude.dudeproject.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "user_response_tbl")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class response {

    @Id
    private String user_id;
    private String user_response;
}