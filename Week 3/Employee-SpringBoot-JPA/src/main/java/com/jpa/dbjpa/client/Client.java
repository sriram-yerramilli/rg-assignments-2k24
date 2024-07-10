package com.jpa.dbjpa.client;

import com.jpa.dbjpa.Employee;
import com.jpa.dbjpa.repository.EmployeeRepository;
import org.springframework.stereotype.Component;


@Component
public class Client {

    private final EmployeeRepository rep;

    public Client(EmployeeRepository rep){
        this.rep = rep;
    }

    public void addNewEmployee(Employee e){
        System.out.println();
        Employee emp = rep.save(e);
        long id = emp.getId();
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

    public void printEmployeeById(long id) {
        System.out.println("Employee with id " + id + ": ");
        Employee emp = rep.findById(id).orElse(null);
        if(emp == null)
            System.out.println("Id not found");
        else
            System.out.println(emp);

    }

    public void updateEmployee(long id, Employee e){
        Employee emp = rep.findById(id).orElse(null);
        if(emp == null)
            System.out.println("Id not found");
        else {
            emp.setName(e.getName());
            emp.setDepartment(e.getDepartment());
            rep.save(emp);
            System.out.println("Updation Successful");
            printEmployees();
        }
    }

    public void deleteEmployee(long id){

        rep.deleteById(id);
        System.out.println("Deletion Successful");
        printEmployees();


    }
}
