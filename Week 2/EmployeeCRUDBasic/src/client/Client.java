package client;

import pojo.Employee;
import service.EmployeeCRUDBasic;

import java.util.ArrayList;

public class Client {

    public static void printEmployees(){
        System.out.println("List of Employees: ");
        ArrayList<Employee> employees = EmployeeCRUDBasic.getEmployees();
        if(employees.isEmpty())
            System.out.println("Empty List");
        else
            employees.forEach(System.out::println);
    }

    public static void printEmployeeById(int id){
        System.out.println("pojo.Employee with id " + id + ": ");
        Employee emp = EmployeeCRUDBasic.getEmployeeById(id);
        if(emp == null)
            System.out.println("Id not found");
        else
            System.out.println(emp);

    }

    public static void addNewEmployee(Employee e){

        Employee emp3 = EmployeeCRUDBasic.addEmployee(e);

        if(emp3 != null) {
            System.out.println("Creation Successful: " + emp3);
        }
        else {
            System.out.println("Creation Failed");
        }
    }

    public static void updateEmployee(int id, Employee e){
        Employee emp = EmployeeCRUDBasic.updateEmployeeById(2, e);
        if(emp != null) {
            System.out.println("Succesfully Updated pojo.Employee with ID " + id + ": " + emp);
        }
    }

    public static void deleteEmployee(int id){
        if(EmployeeCRUDBasic.deleteEmployeeById(id)) {
            System.out.println("Deletion Successful");
            printEmployees();
        }
        else {
            System.out.println("Deletion Failed");
        }

    }

    public static void main(String[] args) {

        Employee emp1 = new Employee(1, "Ram", "Risk");
        Employee emp2 = new Employee(2, "Raj", "Merchant");
        Employee emp3 = new Employee(3, "Rohi", "Complaince");

        Client.addNewEmployee(emp1);
        Client.addNewEmployee(emp2);
        Client.addNewEmployee(emp3);

        System.out.println("*************************************************\n");

        Client.printEmployees();

        System.out.println("*************************************************\n");

        Client.printEmployeeById(3);
        Client.printEmployeeById(4);

        System.out.println("*************************************************\n");

        System.out.println("Update pojo.Employee with ID 2 with pojo.Employee ID 3: ");
        Client.updateEmployee(2, emp3);

        System.out.println("*************************************************\n");

        System.out.println("Deleting pojo.Employee with ID 3: ");
        Client.deleteEmployee(3);


    }

}
