package service;

import pojo.Employee;

import java.sql.*;
import java.util.ArrayList;

/* Establishes Singleton JDBC Connection */
class JDBCConnection {
    // JDBC URL, username and password of Oracle database server
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String user = "system";
    private static String password = "system";

    public static Connection con = null;

    public static Connection getConnection() {
        if(con == null) {
            try {
                con = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return con;
    }

}

/* performs CRUD on employee table in ORACLE DB */
public class EmployeeJDBC {

    private static final Connection con = JDBCConnection.getConnection();

    /* creates an employee and adds it to the list of employees */
    public static Employee addEmployee(Employee employee) {
        String sql = "insert into employee values(?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, (employee.getId()));
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getDepartment());
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(ps != null) ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return employee;
    }

    /* Fetches the employee by id */
    public static Employee getEmployeeById(int id) {
        Employee employee = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from employee where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department"));
//                System.out.println(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    /* updates name and dept of employee by id */
    public static Employee updateEmployeeById(int id, Employee employee) {
        PreparedStatement ps = null;
        String sql = "update employee set name = ?, department = ? where id = ?";
        Employee emp1 = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getDepartment());
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                emp1 = employee;
            }
            else {
                System.out.println("No pojo.Employee with id " + id + " found, Creating new pojo.Employee");
                emp1 = addEmployee(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(ps != null) ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return emp1;
    }

    /* Deletes employee with the given id */
    public static Boolean deleteEmployeeById(int id) {
        String sql = "delete from employee where id = ?";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return true;
            }
            else
                return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    /* Fetches all the employees */
    public static ArrayList<Employee> getEmployees() {
        Statement st = null;
        String sql = "select * from employee";
        ResultSet rs = null;
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department"));
                employees.add(employee);
            }
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void closeConnection(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
