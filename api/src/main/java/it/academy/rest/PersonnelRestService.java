package it.academy.rest;

import io.swagger.annotations.ApiOperation;
import it.academy.dto.DepartmentEmployeesPhoneNumbersDTO;
import it.academy.pojos.Department;
import it.academy.pojos.Employee;
import it.academy.service.DepartmentService;
import it.academy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public class PersonnelRestService {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/departments")
    public List<DepartmentEmployeesPhoneNumbersDTO> getAllDepartments() {

        return departmentService.findAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable String id) {
        Department department = departmentService.findDepartment(id);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping(value = "/departments", consumes = "application/json")
    @ApiOperation("create department")
    public ResponseEntity<String> createDepartment(@RequestBody Department department) {
        String id = departmentService.createDepartment(department);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity deleteDepartment(@PathVariable String id) {

        if (departmentService.deleteDepartment(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String id) {
        Employee employee = employeeService.findEmployee(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping(value = "/employees", consumes = "application/json")
    @ApiOperation("create employee")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        String id = employeeService.createEmployee(employee);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteEmployees(@PathVariable String id) {
        if (employeeService.deleteEmployee(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //Add employee to department PUT
    @PutMapping(value = "/employees/add/{empId}/{depId}")
    public ResponseEntity<Department> addToDepartment(@PathVariable String depId,
                                                      @PathVariable String empId) {
        if (depId == null || empId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Department department = departmentService.findDepartment(depId);
        if (department != null) {
            Employee employee = employeeService.findEmployee(empId);
            if (employee != null) {
                if (employeeService.addEmployeeToDepartment(empId, depId)) {
                    return new ResponseEntity<>(department, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Get all employees which do not belong to any department GET
    @GetMapping("/departments/")
    public List<Employee> getAllEmployeesWithoutDepartments() {
        return employeeService.findAllEmployeesWithoutDepartment();
    }

    //Remove employee from department PUT
    @PutMapping(value = "/employees/remove/{empId}/{depId}")
    public ResponseEntity<Department> removeFromDepartment(@PathVariable String depId,
                                                           @PathVariable String empId) {

        if (depId == null || empId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Department department = departmentService.findDepartment(depId);
        Employee employee = employeeService.findEmployee(empId);
        if (department == null || employee == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        boolean success = employeeService.removeEmployeeFromDepartment(empId, depId);
        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
