package it.academy.service;

import it.academy.dao.DepartmentDao;
import it.academy.dao.EmployeeDao;
import it.academy.pojos.Department;
import it.academy.pojos.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;


    public Employee findEmployee(String id) {
        return employeeDao.getEmployee(id);
    }

    public String createEmployee(Employee employee) {
        return employeeDao.createEmployee(employee);
    }

    public boolean deleteEmployee(String id) {
        return employeeDao.deleteEmployee(id);
    }

    public void addEmployeeToDepartment(String employeeId, String departmentId) {

        Employee employee = employeeDao.getEmployee(employeeId);
        Department department = departmentDao.getDepartment(departmentId);

        List<Employee> employees = department.getEmployees();
        employees.add(employee);
        department.setEmployees(employees);

        departmentDao.updateDepartment(department);
    }

    public void removeEmployeeFromDepartment(String employeeId, String departmentId) {

        Employee employee = employeeDao.getEmployee(employeeId);
        Department department = departmentDao.getDepartment(departmentId);

        List<Employee> employees = department.getEmployees();
        employees.remove(employee);
        department.setEmployees(employees);

        departmentDao.updateDepartment(department);
    }

    public List<Employee> findAllEmployeesWithoutDepartment() {
        return employeeDao.getAllEmployeesInDepartment(null);
    }
}
