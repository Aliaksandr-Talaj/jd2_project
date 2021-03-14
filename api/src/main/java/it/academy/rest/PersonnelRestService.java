package it.academy.rest;

import io.swagger.annotations.ApiOperation;
import it.academy.pojos.Department;
import it.academy.pojos.Employee;
import it.academy.service.DepartmentService;
import it.academy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonnelRestService {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
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
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        departmentService.createDepartment(department);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
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
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
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

        employeeService.addEmployeeToDepartment(empId, depId);
        return new ResponseEntity<>(HttpStatus.OK);
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

        employeeService.removeEmployeeFromDepartment(empId, depId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
