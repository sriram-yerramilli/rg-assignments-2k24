package service;

import pojo.Employee;

import java.util.ArrayList;
import java.util.Optional;

public class EmployeeCRUDBasic {

    public static ArrayList<Employee> employees = new ArrayList<>();

    /* creates an employee and adds it to the list of employees */
    public static Employee addEmployee(Employee employee) {
        if(employees.add(employee))
            return employee;
        else
            return null;
    }

    /* Fetches the employee by id */
    public static Employee getEmployeeById(int id) {
        Optional<Employee> employee = employees.stream()
                                        .filter(e -> e.getId() == id)
                                        .findFirst();
        return employee.orElse(null);
    }

    /* updates name and dept of employee by id */
    public static Employee updateEmployeeById(int id, Employee employee) {
        Optional<Employee> emp = employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        Employee emp1 = null;
        if(emp.isPresent())
        {
            emp1 = emp.get();
            emp1.setName(employee.getName());
            emp1.setDepartment(employee.getDepartment());
        }
        else
        {
            System.out.println("No pojo.Employee with id " + id + " found, Creating new pojo.Employee");
            emp1 = addEmployee(employee);
        }
        return emp1;
    }

    /* Deletes employee with the given id */
    public static Boolean deleteEmployeeById(int id) {
        Employee emp = getEmployeeById(id);
        if(emp == null)
            return false;
        else {
            employees.remove(emp);
            return true;
        }
    }
    /* Fetches all the employees */
    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

}
