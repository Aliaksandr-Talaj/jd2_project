package it.academy.dao;

import it.academy.pojos.Department;

import java.util.List;

public interface DepartmentDao {

    //    - Get all departments with their users
    List<Department> getAllDepartments();

    //  - Get one department
    Department getDepartment(String id);

    //  - Create department
    String createDepartment(Department department);

    //  - Delete department
    boolean deleteDepartment(String id);

    void updateDepartment(Department department);
}
