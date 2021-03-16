package it.academy.dto;

import it.academy.pojos.Department;
import it.academy.pojos.Employee;
import it.academy.pojos.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class DepartmentEmployeesPhoneNumbersDTO {

    private Department department;

    private List<Employee> employees;

    private List<PhoneNumber> phoneNumbers;
}
