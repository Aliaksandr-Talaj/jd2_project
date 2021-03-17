package it.academy.service;

import it.academy.dao.interfaces.DepartmentDao;
import it.academy.dao.interfaces.EmployeeDao;
import it.academy.dao.interfaces.PhoneNumberDao;
import it.academy.dao.interfaces.PositionDao;
import it.academy.pojos.Department;
import it.academy.pojos.Employee;
import it.academy.pojos.PhoneNumber;
import it.academy.pojos.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    PhoneNumberDao phoneNumberDao;
    @Autowired
    PositionDao positionDao;



    public Employee findEmployee(String id) {
        return employeeDao.getEmployee(id);
    }

    public String createEmployee(Employee employee) {
        return employeeDao.createEmployee(employee);
    }

    public boolean deleteEmployee(String emplpyeeId) {
        List<PhoneNumber> phoneNumbers = phoneNumberDao.getPhoneNumbersOfEmployee(emplpyeeId);
        phoneNumbers.forEach(p -> {
            p.setEmployee(null);
            phoneNumberDao.updatePhoneNumber(p);
        });

        List<Position> positions = positionDao.getPositionsOfEmployee(emplpyeeId);
        positions.forEach(position -> {
            position.setEmployee(null);
            positionDao.updatePosition(position);
        });

        return employeeDao.deleteEmployee(emplpyeeId);
    }

    public boolean addEmployeeToDepartment(String employeeId, String departmentId) {

        if (employeeId == null || departmentId == null) {
            return false;
        }
        Department department = departmentDao.getDepartment(departmentId);
        if (department != null) {
            Employee employee = findEmployee(employeeId);
            if (employee != null) {
                employee.setDepartment(department);
                List<Employee> employees = employeeDao.
                        getAllEmployeesInDepartment(departmentId);
                if (employees == null) {
                    employees = new ArrayList<>();
                }
                employees.add(employee);
                department.setEmployees(employees);
                departmentDao.updateDepartment(department);
                return true;
            }
        }
        return false;
    }

    public String addNewEmployeeToDepartment(Employee employee, String departmentId) {

        String employeeId = employeeDao.createEmployee(employee);
        employee = employeeDao.getEmployee(employeeId);
        Department department = departmentDao.getDepartment(departmentId);

        List<Employee> employees = department.getEmployees();
        employees.add(employee);
        department.setEmployees(employees);

        departmentDao.updateDepartment(department);
        return employeeId;
    }


    public boolean removeEmployeeFromDepartment(String employeeId, String departmentId) {

        Employee employee = employeeDao.getEmployee(employeeId);
        Department department = departmentDao.getDepartment(departmentId);

        List<Employee> employees = department.getEmployees();
        employees.remove(employee);
        department.setEmployees(employees);

        departmentDao.updateDepartment(department);

        return employeeDao
                .getEmployee(employeeId)
                .getDepartment() == null;
    }

    public List<Employee> findAllEmployeesWithoutDepartment() {
        return employeeDao.getAllEmployeesInDepartment(null);
    }
}
