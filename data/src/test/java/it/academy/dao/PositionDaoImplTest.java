package it.academy.dao;

import it.academy.dao.interfaces.PositionDao;
import it.academy.pojos.Employee;
import it.academy.pojos.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(classes = DaoTestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PositionDaoImplTest {

    @Autowired
    PositionDao positionDao;
    @Autowired
    SessionFactory sessionFactory;

    @Test
    public void createPosition() {
        //Given
        Position position = new Position();
        String purpose = "to suffer";
        position.setPurpose(purpose);

        //When
        String id = positionDao.createPosition(position);
        //Then
        assertEquals(purpose, positionDao.getPosition(id).getPurpose());
    }

    @Test
    public void getPosition() {
        //Given
        Position position = new Position();
        String purpose = "to suffer";
        position.setPurpose(purpose);

        //When
        String id = positionDao.createPosition(position);
        //Then
        assertEquals(purpose, positionDao.getPosition(id).getPurpose());
    }

    @Test
    public void getAllPositions() {
        //Given
        Position position = new Position();
        Position position1 = new Position();
        Position position2 = new Position();
        positionDao.createPosition(position);
        positionDao.createPosition(position1);
        positionDao.createPosition(position2);

        //When
        List<Position> positionList = positionDao.getAllPositions();
        //Then
        assertTrue(positionList.size() >= 3);
    }

    @Test
    public void getPositionsOfEmployee() {
        //Given
        Position position = new Position();
        Position position1 = new Position();
        Position position2 = new Position();
        Employee employee = new Employee();
        position.setEmployee(employee);
        position1.setEmployee(employee);
        position2.setEmployee(employee);
        List<Position> positions = List.of(position, position1, position2);
        employee.setPositions(positions);
        String id;
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(position);
            session.save(position1);
            session.save(position2);
            id = (String) session.save(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }

        //When
        List<Position> positionList = positionDao.getPositionsOfEmployee(id);
        //Then
        assertEquals(3, positionList.size());
    }


    @Test
    public void updatePosition() {
        //Given
        Position position = new Position();

        String purpose = "to suffer";
        position.setPurpose("to be happy");
        String id = positionDao.createPosition(position);
        //When
        position = positionDao.getPosition(id);
        position.setPurpose(purpose);
        positionDao.updatePosition(position);
        //Then
        assertEquals(purpose, positionDao.getPosition(id).getPurpose());
    }


    @Test
    public void deletePosition() {

        //Given
        Position position = new Position();

        String id = positionDao.createPosition(position);
        assertNotNull(positionDao.getPosition(id));
        //When
        positionDao.deletePosition(id);
        //Then
        assertNull(positionDao.getPosition(id));

    }
}