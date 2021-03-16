package it.academy.dao.interfaces;

import it.academy.pojos.Employee;

import java.util.List;

public interface EmployeeDao {


    List<Employee> getAllEmployeesInDepartment(String departmentId);

    //  - Get one employee
    Employee getEmployee(String id);

    //  - Create employee
    String createEmployee(Employee employee);

    //  - Delete employee
    boolean deleteEmployee(String id);

    void updateEmployee(Employee employee);

}






