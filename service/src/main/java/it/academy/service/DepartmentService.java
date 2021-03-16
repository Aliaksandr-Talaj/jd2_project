package it.academy.service;

import it.academy.dao.interfaces.DepartmentDao;
import it.academy.dao.interfaces.EmployeeDao;
import it.academy.dao.interfaces.PhoneNumberDao;
import it.academy.pojos.Department;
import it.academy.pojos.Employee;
import it.academy.pojos.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    PhoneNumberDao phoneNumberDao;


    public List<Department> findAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    public Department findDepartment(String id) {
        return departmentDao.getDepartment(id);
    }

    public String createDepartment(Department department) {
        return departmentDao.createDepartment(department);
    }

    public void updateDepartment(Department department) {
        departmentDao.updateDepartment(department);
    }

    public boolean deleteDepartment(String depId) {

        List<Employee> employees = employeeDao.getAllEmployeesInDepartment(depId);
        employees.forEach(p -> {
            p.setDepartment(null);
            employeeDao.updateEmployee(p);
        });

        List<PhoneNumber> phoneNumbers = phoneNumberDao.getPhoneNumbersOfDepartment(depId);
        phoneNumbers.forEach(p -> p.setDepartment(null));

        return departmentDao.deleteDepartment(depId);
    }
}
