package it.academy.rest;

import io.swagger.annotations.ApiOperation;
import it.academy.dto.DepartmentEmployeesPhoneNumbersDTO;
import it.academy.pojos.Department;
import it.academy.service.DepartmentService;
import it.academy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/departments/pages")
    public List<DepartmentEmployeesPhoneNumbersDTO> getAllDepartments(@RequestParam("pageNum") Integer pageNum,
                                                                      @RequestParam("pageSize") Integer pageSize
    ) {
List<DepartmentEmployeesPhoneNumbersDTO> list = departmentService.findAllDepartments();
        System.out.println(list.size());
        return paginate(list ,pageNum,pageSize);
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

    private List<DepartmentEmployeesPhoneNumbersDTO> paginate(List<DepartmentEmployeesPhoneNumbersDTO> inputList,
                                                              Integer pageNum,
                                                              Integer pageSize) {
        List<DepartmentEmployeesPhoneNumbersDTO> outputList = new ArrayList<>();
        if (inputList == null) {
            return outputList;
        }
        int size = inputList.size();
        if (size <= pageSize) {
            return inputList;
        }
        if (pageNum * pageSize <= size) {
            int i = (pageNum - 1) * pageSize;
            do {
                outputList.add(inputList.get(i));
                i++;
            } while (pageNum * pageSize > i);
            return outputList;
        }
        int choppedPageSize = size % pageSize;
        if (choppedPageSize == 0) {
            choppedPageSize = pageSize;
        }

        for (int i = size - choppedPageSize; i < size; i++) {
            outputList.add(inputList.get(i));
        }
        return outputList;
    }
}

