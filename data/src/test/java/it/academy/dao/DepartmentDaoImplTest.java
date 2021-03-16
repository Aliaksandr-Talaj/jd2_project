package it.academy.dao;

import it.academy.dao.interfaces.DepartmentDao;
import it.academy.pojos.Department;
import it.academy.pojos.Description;
import it.academy.pojos.Employee;
import it.academy.pojos.PhoneNumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(classes = DaoTestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentDaoImplTest {

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void getAllDepartments() {
        //Given

        Department department = new Department();
        Department department1 = new Department();

        String id = departmentDao.createDepartment(department);
        String id2 = departmentDao.createDepartment(department1);
        //When
        List<Department> departments = departmentDao.getAllDepartments();

        //Then
        assertTrue(departments.size() >= 2);
    }

    @Test
    public void getDepartment() {
        //Given
        Department department = new Department();
        PhoneNumber phoneNumber = new PhoneNumber();
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(phoneNumber);
        department.setPhoneNumber(phoneNumbers);
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employees.add(employee);
        department.setEmployees(employees);
        department.setDescription(new Description());
        department.setDateOfFormation(new Date(112, 12, 12));
        String id = departmentDao.createDepartment(department);
        //When
        departmentDao.getDepartment(id);

        //Then
        assertEquals("2013-01-12", departmentDao.getDepartment(id).getDateOfFormation().toString());

    }

    @Test
    public void createDepartment() {
        //Given
        Department department = new Department();
        PhoneNumber phoneNumber = new PhoneNumber();
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(phoneNumber);
        department.setPhoneNumber(phoneNumbers);
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employees.add(employee);
        department.setEmployees(employees);
        department.setDescription(new Description());
        department.setDateOfFormation(new Date(112, 12, 12));

        //When
        String id = departmentDao.createDepartment(department);

        //Then
        assertNotNull(departmentDao.getDepartment(id));
    }

    @Test
    public void deleteDepartment() {
        //Given
        Department department = new Department();
        String id = departmentDao.createDepartment(department);
        assertNotNull(departmentDao.getDepartment(id));

        //When
       assertTrue(departmentDao.deleteDepartment(id));

        //Then
        assertNull(departmentDao.getDepartment(id));
    }

    @Test
    public void updateDepartment() {
        //Given
        Department department = new Department();
        department.setDateOfFormation(new Date(112, 12, 12));
        String id = departmentDao.createDepartment(department);
        department = departmentDao.getDepartment(id);
        department.setName("name");
        //When
        departmentDao.updateDepartment(department);

        //Then
        assertEquals("name", departmentDao.getDepartment(id).getName());

    }
}