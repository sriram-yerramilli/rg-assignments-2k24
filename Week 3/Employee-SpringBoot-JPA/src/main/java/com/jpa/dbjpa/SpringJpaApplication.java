package com.jpa.dbjpa;

import com.jpa.dbjpa.client.Client;
import com.jpa.dbjpa.Employee;
import com.jpa.dbjpa.repository.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringJpaApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringJpaApplication.class, args);
        Client client = ctx.getBean(Client.class);
        Employee e = new Employee();
        e.setName("ram");
        e.setDepartment("Risk");
        ctx.getBean(EmployeeRepository.class);
//        client.printEmployees();
//        client.addNewEmployee(e);
        client.printEmployees();
        e.setName("Reshma");
        client.updateEmployee(4,e);
        client.printEmployees();
        client.deleteEmployee(1);
    }

}
