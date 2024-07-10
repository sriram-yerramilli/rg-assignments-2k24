package com.jdbc.dbjdbc.client;

import com.jdbc.dbjdbc.pojo.Employee;
import com.jdbc.dbjdbc.repository.JdbcEmployeeRepository;

import org.springframework.stereotype.Component;


@Component
public class Client {

    private final JdbcEmployeeRepository rep;

    public Client(JdbcEmployeeRepository rep){
        this.rep = rep;
    }

    public void addNewEmployee(Employee e){
        System.out.println();
        long id = rep.save(e);
        if(id > 0)
            System.out.println("Insertion Successful, id: " + id);
        else
            System.out.println("Insertion Failed");
    }

    public void printEmployees(){
        System.out.println("List of Employees: ");
        Iterable<Employee> employees = rep.findAll();
        employees.forEach(System.out::println);
    }

    public void printEmployeeById(int id) {
        System.out.println("Employee with id " + id + ": ");
        Employee emp = rep.findById(id);
        if(emp == null)
            System.out.println("Id not found");
        else
            System.out.println(emp);

    }

    public void updateEmployee(int id, Employee e){
        rep.updateById(id, e);
        System.out.println("Updation Successful");
        printEmployees();
    }

    public void deleteEmployee(int id){

        rep.deleteById(id);
        System.out.println("Deletion Successful");
        printEmployees();


    }
}
