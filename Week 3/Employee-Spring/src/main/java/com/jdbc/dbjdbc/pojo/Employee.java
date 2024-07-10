package com.jdbc.dbjdbc.pojo;

import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor

@Component
public class Employee {
    @Generated
    private int id;

    private String name;

    private String department;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

