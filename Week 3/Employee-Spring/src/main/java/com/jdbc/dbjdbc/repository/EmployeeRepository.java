package com.jdbc.dbjdbc.repository;

import com.jdbc.dbjdbc.pojo.Employee;

public interface EmployeeRepository {

    Iterable<Employee>findAll();
    Employee findById(int id);
    long save(Employee employee);
    Employee updateById(int id, Employee employee);
    void deleteById(int id);
}

