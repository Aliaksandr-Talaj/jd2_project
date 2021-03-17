package it.academy.dao;

import it.academy.dao.interfaces.PhoneNumberDao;
import it.academy.pojos.Department;
import it.academy.pojos.Employee;
import it.academy.pojos.PhoneNumber;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@ContextConfiguration(classes = DaoTestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PhoneNumberDaoImplTest {

    @Autowired
    PhoneNumberDao phoneNumberDao;
    @Autowired
    SessionFactory sessionFactory;


    @Test
    public void getPhoneNumber() {

        //Given
        PhoneNumber phoneNumber = new PhoneNumber();
        String number = "2020327";
        phoneNumber.setNumber(number);

        //When
        String id = phoneNumberDao.createPhoneNumber(phoneNumber);
        //Then
        assertEquals(number, phoneNumberDao.getPhoneNumber(id).getNumber());


    }

    @Test
    public void getAllPhoneNumbers() {
        //Given
        PhoneNumber phoneNumber = new PhoneNumber();
        PhoneNumber phoneNumber1 = new PhoneNumber();
        phoneNumberDao.createPhoneNumber(phoneNumber);
        phoneNumberDao.createPhoneNumber(phoneNumber1);

        //When
        List<PhoneNumber> phoneNumbers = phoneNumberDao.getAllPhoneNumbers();

        //Then
        assertTrue(phoneNumbers.size() >= 2);

    }

    @Test
    public void getPhoneNumbersOfDepartment() {
        //Given
        PhoneNumber phoneNumber = new PhoneNumber();
        PhoneNumber phoneNumber1 = new PhoneNumber();
        PhoneNumber phoneNumber2 = new PhoneNumber();
        Department department = new Department();
        phoneNumber.setDepartment(department);
        phoneNumber1.setDepartment(department);
        phoneNumber2.setDepartment(department);
        List<PhoneNumber> phoneNumbers = List.of(phoneNumber, phoneNumber1, phoneNumber2);
        department.setPhoneNumber(phoneNumbers);
        String id;
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(phoneNumber);
            session.save(phoneNumber1);
            session.save(phoneNumber2);
            id = (String) session.save(department);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }

        //When
        List<PhoneNumber> phoneNumberList = phoneNumberDao.getPhoneNumbersOfDepartment(id);
        //Then
        assertEquals(3, phoneNumberList.size());
    }

    @Test
    public void getPhoneNumbersOfEmployee() {
        //Given
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber("111");
        PhoneNumber phoneNumber1 = new PhoneNumber();
        phoneNumber1.setNumber("222");
        PhoneNumber phoneNumber2 = new PhoneNumber();
        phoneNumber2.setNumber("333");
        Employee employee = new Employee();
        phoneNumber.setEmployee(employee);
        phoneNumber1.setEmployee(employee);
        phoneNumber2.setEmployee(employee);
        List<PhoneNumber> phoneNumbers = List.of(phoneNumber, phoneNumber1, phoneNumber2);
        employee.setPhoneNumbers(phoneNumbers);
        String id;
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(phoneNumber);
            session.save(phoneNumber1);
            session.save(phoneNumber2);
            id = (String) session.save(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }

        //When
        List<PhoneNumber> phoneNumberList = phoneNumberDao.getPhoneNumbersOfEmployee(id);
        //Then
        assertEquals(3, phoneNumberList.size());
    }

    @Test
    public void createPhoneNumber() {
        //Given
        PhoneNumber phoneNumber = new PhoneNumber();
        String number = "2020327";
        phoneNumber.setNumber(number);

        //When
        String id = phoneNumberDao.createPhoneNumber(phoneNumber);
        //Then
        assertEquals(number, phoneNumberDao.getPhoneNumber(id).getNumber());
    }

    @Test
    public void updatePhoneNumber() {
        //Given
        PhoneNumber phoneNumber = new PhoneNumber();
        String number = "2020327";
        phoneNumber.setNumber("911");
        String id = phoneNumberDao.createPhoneNumber(phoneNumber);
        //When
        phoneNumber = phoneNumberDao.getPhoneNumber(id);
        phoneNumber.setNumber(number);
        phoneNumberDao.updatePhoneNumber(phoneNumber);
        //Then
        assertEquals(number, phoneNumberDao.getPhoneNumber(id).getNumber());
    }

    @Test
    public void deletePhoneNumber() {
        //Given
        PhoneNumber phoneNumber = new PhoneNumber();
        String id = phoneNumberDao.createPhoneNumber(phoneNumber);
        assertNotNull(phoneNumberDao.getPhoneNumber(id));
        //When
        phoneNumberDao.deletePhoneNumber(id);
        //Then
        assertNull(phoneNumberDao.getPhoneNumber(id));
    }
}