package com.jpa.dbjpa.repository;


import com.jpa.dbjpa.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}