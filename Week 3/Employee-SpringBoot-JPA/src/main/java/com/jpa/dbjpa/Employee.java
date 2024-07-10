package com.jpa.dbjpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {

    @Generated
    @Id
    private int id;
    private String name;
    private String department;


}
