package com.jdbc.dbjdbc;

import com.jdbc.dbjdbc.client.Client;
import com.jdbc.dbjdbc.pojo.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringJdbcApplication {

    public static void main(String[] args) {
       ApplicationContext applicationContext =  SpringApplication.run(SpringJdbcApplication.class, args);
       System.out.println("Hello World");
       Client client = applicationContext.getBean(Client.class);
       Employee employee = applicationContext.getBean(Employee.class);
       employee.setName("Ram");
       employee.setDepartment("Risk");

//       client.addNewEmployee(employee);

       employee.setName("Raj");
       employee.setDepartment("Compliance");


//        client.addNewEmployee(employee);
        client.deleteEmployee(2);
        client.updateEmployee(1, employee);
    }

}
