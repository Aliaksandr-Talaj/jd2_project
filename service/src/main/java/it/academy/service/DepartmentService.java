package it.academy.service;

import it.academy.dao.DepartmentDao;
import it.academy.dao.EmployeeDao;
import it.academy.pojos.Department;
import it.academy.pojos.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    EmployeeDao employeeDao;


    public List<Department> findAllDepartments() {

//        List<DepartmentWithEmployeesDto> departmentWithEmployeesDtoList = new ArrayList<>();
//        List<Department> departments = departmentDao.getAllDepartments();
//
//        for (Department department : departments) {
//            List<Employee> employees = employeeDao.getAllEmployeesInDepartment(department.getId());
//            departmentWithEmployeesDtoList.add(new DepartmentWithEmployeesDto(department, employees));
//        }
        return departmentDao.getAllDepartments();
    }

    public Department findDepartment(String id) {
        return departmentDao.getDepartment(id);
    }

    public String createDepartment(Department department) {
        return departmentDao.createDepartment(department);
    }

    public boolean deleteDepartment(String depId) {

        List<Employee> employees = employeeDao.getAllEmployeesInDepartment(depId);

        for (Employee employee : employees) {
            employee.setDepartment(null);
            employeeDao.updateEmployee(employee);
        }
        return departmentDao.deleteDepartment(depId);
    }
}
