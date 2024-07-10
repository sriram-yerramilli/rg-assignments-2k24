package com.jdbc.dbjdbc.repository;

import com.jdbc.dbjdbc.pojo.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {

    private final JdbcTemplate jdbc;
    private RowMapper<Employee> rowMapper;
    private final SimpleJdbcInsert employeeInserter;

    public JdbcEmployeeRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.employeeInserter = new SimpleJdbcInsert(this.jdbc)
                .withTableName("Employee")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public Iterable<Employee> findAll() {
        String sql = "select * from employee";
        return jdbc.query(sql, (rs, RowNum) -> {
            Employee employee = new Employee();
            employee.setId(rs.getInt(1));
            employee.setName(rs.getString(2));
            employee.setDepartment(rs.getString(3));
            return employee;
        });
    }

    @Override
    public Employee findById(int id) {
        String sql = "select * from employee where id = ?";
        return jdbc.queryForObject(sql,(rs, row) -> {
            Employee employee = new Employee();
            employee.setId(rs.getInt(1));
            employee.setName(rs.getString(2));
            employee.setDepartment(rs.getString(3));
            return employee;
        }, id);
    }

    @Override
    public long save(Employee employee) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("name", employee.getName());
        params.put("department", employee.getDepartment());
        String sql = "insert into employee values(?,?)";
        return employeeInserter.executeAndReturnKey(params).longValue();
    }

    @Override
    public Employee updateById(int id, Employee employee) {

        String sql = "update employee set name = ?, department = ? where id = ?";
        System.out.println(employee);
        jdbc.update(sql, employee.getName(), employee.getDepartment(), id);
        return employee;
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from employee where id = ?";
        jdbc.update(sql, id);
    }



}
