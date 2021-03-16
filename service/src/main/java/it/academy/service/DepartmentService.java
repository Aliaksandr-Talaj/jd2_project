package it.academy.service;

import it.academy.dao.interfaces.DepartmentDao;
import it.academy.dao.interfaces.EmployeeDao;
import it.academy.dao.interfaces.PhoneNumberDao;
import it.academy.dto.*;
import it.academy.pojos.Department;
import it.academy.pojos.Employee;
import it.academy.pojos.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;

@Service
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class DepartmentService {

    @Autowired

    DepartmentDao departmentDao;
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    PhoneNumberDao phoneNumberDao;


    public List<DepartmentEmployeesPhoneNumbersDTO> findAllDepartments() {

        List<DepartmentEmployeesPhoneNumbersDTO> dtos = new ArrayList<>();

        List<Department> departments = departmentDao.getAllDepartments();

        for (Department department : departments) {
            String depId = department.getId();
            List<Employee> employees = employeeDao.getAllEmployeesInDepartment(depId);
            List<PhoneNumber> phoneNumbers = phoneNumberDao.getPhoneNumbersOfDepartment(depId);
            DepartmentEmployeesPhoneNumbersDTO dto =
                    new DepartmentEmployeesPhoneNumbersDTO(department, employees, phoneNumbers);
            dtos.add(dto);
        }

        return dtos;
    }

    public Department findDepartment(String id) {
        return departmentDao.getDepartment(id);
    }

    public String createDepartment(Department department) {
        return departmentDao.createDepartment(department);
    }

    public boolean updateDepartment(Department department) {
        String id = department.getId();
        if (id != null) {
            Department dep = departmentDao.getDepartment(id);
            if (dep != null) {
                departmentDao.updateDepartment(department);
                dep = departmentDao.getDepartment(id);
                return (department.equals(dep));
            }
        }
        return false;

    }

    public boolean deleteDepartment(String depId) {

        List<Employee> employees = employeeDao.getAllEmployeesInDepartment(depId);
        employees.forEach(p -> {
            p.setDepartment(null);
            employeeDao.updateEmployee(p);
        });

        List<PhoneNumber> phoneNumbers = phoneNumberDao.getPhoneNumbersOfDepartment(depId);
        phoneNumbers.forEach(p -> {
            p.setDepartment(null);
            phoneNumberDao.updatePhoneNumber(p);
        });

        return departmentDao.deleteDepartment(depId);
    }
}
