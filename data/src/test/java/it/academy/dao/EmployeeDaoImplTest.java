package it.academy.dao;

import it.academy.dao.interfaces.EmployeeDao;
import it.academy.pojos.Department;
import it.academy.pojos.Employee;
import it.academy.pojos.FullName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
public class EmployeeDaoImplTest {

    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void getAllEmployeesInDepartment() {
        //Given

        Department department = new Department();
        Employee employee = new Employee();
        department.setName("12");
        employee.setFullName(new FullName());
        employee.setDepartment(department);
        Employee employee1 = new Employee();
        employee1.setDepartment(department);
        Employee employee2 = new Employee();
        employee2.setDateOfBirth(new Date(98, 12, 12));
        employee2.setDepartment(department);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee1);
        employees.add(employee2);
        department.setEmployees(employees);

        String id;
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(employee);
            session.save(employee1);
            session.save(employee2);
            id = (String) session.save(department);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }

        //When
        List<Employee> employeeList = employeeDao.getAllEmployeesInDepartment(id);
        //Then
        assertEquals(3, employeeList.size());
    }

    @Test
    public void getEmployee() {
        //Given
        Employee employee = new Employee();
        Date date = new Date(98, 12, 12);
        employee.setDateOfBirth(date);
        //When
        String id = employeeDao.createEmployee(employee);
        //Then
        assertEquals(date, employeeDao.getEmployee(id).getDateOfBirth());
    }

    @Test
    public void createEmployee() {
        //Given

        //When
        String id = employeeDao.createEmployee(new Employee());
        //Then
        assertNotNull(employeeDao.getEmployee(id));

    }

    @Test
    public void deleteEmployee() {
        //Given
        Employee employee = new Employee();
        String id = employeeDao.createEmployee(employee);
        assertNotNull(employeeDao.getEmployee(id));
        //When
        employeeDao.deleteEmployee(id);
        //Then
        assertNull(employeeDao.getEmployee(id));

    }

    @Test
    public void updateEmployee() {
        //Given
        Employee employee = new Employee();
        Date date = new Date(98, 12, 12);
        Date date1 = new Date(99, 12, 12);
        employee.setDateOfBirth(date);
        String id = employeeDao.createEmployee(employee);
        assertEquals(date, employeeDao.getEmployee(id).getDateOfBirth());
        employee = employeeDao.getEmployee(id);
        employee.setDateOfBirth(date1);
        //When
        employeeDao.updateEmployee(employee);
        //Then
        assertEquals(date1, employeeDao.getEmployee(id).getDateOfBirth());

    }
}