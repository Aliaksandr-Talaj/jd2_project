package it.academy.dao.interfaces;

import it.academy.pojos.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeDao {


    List<Employee> getAllEmployeesInDepartment(String departmentId);

    //  - Get one employee
    Employee getEmployee(String id);

    @Transactional
    String getDepartmentIdOfEmployee(String empId);

    //  - Create employee
    String createEmployee(Employee employee);

    //  - Delete employee
    boolean deleteEmployee(String id);

    void updateEmployee(Employee employee);

}






