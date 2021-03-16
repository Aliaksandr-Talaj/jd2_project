package it.academy.pojos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EntitiesTest extends BaseTest {


    @Test
    public void createDepartment() {
        //Given
        Department department = new Department();
        department.setEmployees(new ArrayList<Employee>());
        department.setDescription(new Description());
        department.setDateOfFormation(new Date(112, 12, 12));
        department.setPhoneNumber(new ArrayList<PhoneNumber>());

        //When
        Session session = factory.openSession();
        Transaction tx = null;
        Serializable id;
        try {
            tx = session.beginTransaction();

            id = session.save(department);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        //Then
        assertNotNull(id);
        session.close();

    }

    @Test
    public void createEmployee() {
        //Given
        Employee employee = new Employee();
        employee.setFullName(new FullName("N", "P", "S"));
        Department department = new Department();
        employee.setDepartment(department);
        employee.setDateOfEmployment(new Date(112, 12, 12));
        employee.setDateOfBirth(new Date(82, 12, 12));
        employee.setEmailAddress(new ArrayList<EmailAddress>());

        //When
        Session session = factory.openSession();
        Transaction tx = null;
        Serializable id;
        try {
            tx = session.beginTransaction();
            session.save(department);
            id = session.save(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        //Then
        assertNotNull(id);
        session.close();

    }

    @Test
    public void createEmailAddress() {
        //Given
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.setEmail("mail@mail.mail");
        emailAddress.setEmailCategory(ContactCategory.PERSONAL);
        Employee employee = new Employee();
        emailAddress.setEmployee(employee);

        //When
        Session session = factory.openSession();
        Transaction tx = null;
        Serializable id;
        try {
            tx = session.beginTransaction();
            session.save(employee);
            id = session.save(emailAddress);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        //Then
        assertNotNull(id);
        session.close();

    }

    @Test
    public void createPhoneNumber() {
        //Given

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber("1231212");
        phoneNumber.setPhoneNumberCategory(ContactCategory.OFFICIAL);
        phoneNumber.setPhoneOperator(PhoneOperator.LIFE);
        Department department = new Department();
        Employee employee = new Employee();
        phoneNumber.setDepartment(department);
        phoneNumber.setEmployee(employee);

        //When
        Session session = factory.openSession();
        Transaction tx = null;
        Serializable id;
        try {
            tx = session.beginTransaction();
            session.save(department);
            session.save(employee);
            id = session.save(phoneNumber);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        //Then
        assertNotNull(id);
        session.close();

    }

    @Test
    public void createPosition() {
        //Given
        Position position = new Position();
        Employee employee = new Employee();
        position.setEmployee(employee);
        position.setName("Name");
        position.setPurpose("Purpose");

        //When
        Session session = factory.openSession();
        Transaction tx = null;
        Serializable id;
        try {
            tx = session.beginTransaction();
            session.save(employee);
            id = session.save(position);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        //Then
        assertNotNull(id);
        session.close();

    }


    @Test
    public void deleteDepartment() {
        //Given:
        cleanInsert("BaseTest.xml");
        String uuid = "402880ec780f061f01780f06225b0000";

        //When:
        Session session = factory.openSession();
        Department department = session.get(Department.class, uuid);
        department.setPhoneNumber(null);
        department.setEmployees(null);
        List<PhoneNumber> phoneNumbers =
                session
                        .createQuery("from PhoneNumber where department_ID='" + uuid + "'", PhoneNumber.class)
                        .list();
        phoneNumbers.forEach(p -> p.setDepartment(null));
        List<Employee> employees = session
                .createQuery("from Employee where department_ID='" + uuid + "'", Employee.class)
                .list();
        employees.forEach(p -> p.setDepartment(null));

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            phoneNumbers.forEach(session::update);
            employees.forEach(session::update);

            session.delete(department);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }

        // Then:
        assertNull(session.get(Department.class, uuid));
        session.close();
        deleteDataset();
    }

    @Test
    public void deleteEmployee() {
        //Given:
        cleanInsert("BaseTest.xml");
        String uuid = "402880ec780f061f01780f0622690008";

        //When:
        Session session = factory.openSession();
        Employee employee = session.get(Employee.class, uuid);
        List<PhoneNumber> phoneNumbers = session
                .createQuery("from PhoneNumber where employee_id='" + uuid + "'", PhoneNumber.class)
                .list();
        phoneNumbers.forEach(p -> p.setEmployee(null));
        List<EmailAddress> emailAddresses = session
                .createQuery("from EmailAddress where employee_id='" + uuid + "'", EmailAddress.class)
                .list();
        emailAddresses.forEach(p -> p.setEmployee(null));
        List<Position> positions = session
                .createQuery("from Position where employee_id='" + uuid + "'", Position.class)
                .list();
        positions.forEach(p -> p.setEmployee(null));

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            phoneNumbers.forEach(session::save);
            emailAddresses.forEach(session::save);
            positions.forEach(session::save);
            session.delete(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }

        // Then:
        assertNull(session.get(Employee.class, uuid));
        session.close();
        deleteDataset();
    }

    @Test
    public void deleteEmailAddress() {
        //Given:
        cleanInsert("BaseTest.xml");
        String uuid = "402880ec780f061f01780f0622690051";

        //When:
        Session session = factory.openSession();
        EmailAddress emailAddress = session.get(EmailAddress.class, uuid);

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.delete(emailAddress);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }

        // Then:
        assertNull(session.get(EmailAddress.class, uuid));
        session.close();
        deleteDataset();
    }

    @Test
    public void deletePhoneNumber() {
        //Given:
        cleanInsert("BaseTest.xml");
        String uuid = "402880ec780f061f01780f0622690098";

        //When:
        Session session = factory.openSession();
        PhoneNumber phoneNumber = session.get(PhoneNumber.class, uuid);

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.delete(phoneNumber);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }

        // Then:
        assertNull(session.get(PhoneNumber.class, uuid));
        session.close();
        deleteDataset();
    }

    @Test
    public void deletePosition() {
        //Given:
        cleanInsert("BaseTest.xml");
        String uuid = "24";

        //When:
        Session session = factory.openSession();
        Position position = session.get(Position.class, uuid);

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.delete(position);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }

        // Then:
        assertNull(session.get(Position.class, uuid));
        session.close();
        deleteDataset();
    }

    @Test
    public void queryDepartments() {
        //Given:
        cleanInsert("BaseTest.xml");

        //When:
        Session session = factory.openSession();
        List<Department> departments = session
                .createQuery("from Department where DEPARTMENT_NAME like 'Top%'", Department.class)
                .list();
        System.out.println(departments.size());
        //Then:
        assertEquals(2, departments.size());
        deleteDataset();
        session.close();
    }

    @Test
    public void queryEmployees() {
        //Given:
        cleanInsert("BaseTest.xml");

        //When:
        Session session = factory.openSession();
        List<Employee> employees = session
                .createQuery("from Employee where Name like 'N%'", Employee.class)
                .list();

        //Then:
        assertEquals(2, employees.size());
        deleteDataset();
        session.close();
    }

    @Test
    public void queryEmailAddress() {
        //Given:
        cleanInsert("BaseTest.xml");

        //When:
        Session session = factory.openSession();
        List<EmailAddress> emailAddresses = session
                .createQuery("from EmailAddress where Email like '%.com'", EmailAddress.class)
                .list();

        //Then:
        assertEquals(2, emailAddresses.size());
        deleteDataset();
        session.close();
    }

    @Test
    public void queryPhoneNumber() {
        //Given:
        cleanInsert("BaseTest.xml");

        //When:
        Session session = factory.openSession();
        List<PhoneNumber> phoneNumbers = session
                .createQuery("from PhoneNumber where Phone_Operator='LIFE'", PhoneNumber.class)
                .list();

        //Then:
        assertEquals(2, phoneNumbers.size());
        deleteDataset();
        session.close();
    }

    @Test
    public void queryPosition() {
        //Given:
        cleanInsert("BaseTest.xml");

        //When:
        Session session = factory.openSession();
        List<Position> positions = session
                .createQuery("from Position where Name='Courier'", Position.class)
                .list();

        //Then:
        assertEquals(3, positions.size());
        deleteDataset();
        session.close();
    }


}