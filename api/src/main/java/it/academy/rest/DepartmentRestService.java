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

@RestController
public class DepartmentRestService {
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


}

