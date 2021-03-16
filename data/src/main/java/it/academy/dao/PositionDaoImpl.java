package it.academy.dao;

import it.academy.dao.interfaces.PositionDao;
import it.academy.pojos.Position;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PositionDaoImpl implements PositionDao {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public PositionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public String createPosition(Position position) {
        return (String) sessionFactory.getCurrentSession().save(position);
    }

    @Override
    @Transactional
    public Position getPosition(String id) {

        return sessionFactory.getCurrentSession().find(Position.class, id);
    }

    @Override
    @Transactional
    public List<Position> getAllPositions() {

        return sessionFactory
                .getCurrentSession()
                .createQuery("from Position", Position.class)
                .list();
    }

    @Override
    @Transactional
    public List<Position> getPositionsOfEmployee(String employeeId) {

        return sessionFactory
                .getCurrentSession()
                .createQuery("from Position where employee_id='" + employeeId + "'", Position.class)
                .list();
    }

    @Override
    @Transactional
    public void updatePosition(Position position) {
        sessionFactory
                .getCurrentSession()
                .update(position);
    }

    @Override
    @Transactional
    public boolean deletePosition(String positionId) {

        Position position = getPosition(positionId);

        sessionFactory.getCurrentSession().delete(position);
        position = sessionFactory
                .getCurrentSession()
                .find(Position.class, positionId);
        return (position == null);
    }
}
