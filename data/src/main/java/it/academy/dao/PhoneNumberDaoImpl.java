package it.academy.dao;

import it.academy.dao.interfaces.PhoneNumberDao;
import it.academy.pojos.PhoneNumber;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PhoneNumberDaoImpl implements PhoneNumberDao {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public PhoneNumberDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public PhoneNumber getPhoneNumber(String id) {
        return sessionFactory.getCurrentSession().get(PhoneNumber.class, id);
    }

    @Override
    @Transactional
    public List<PhoneNumber> getAllPhoneNumbers() {

        return sessionFactory
                .getCurrentSession()
                .createQuery("from PhoneNumber", PhoneNumber.class)
                .list();
    }

    @Override
    @Transactional
    public List<PhoneNumber> getPhoneNumbersOfDepartment(String depId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from PhoneNumber p where p.department.id='" + depId + "'", PhoneNumber.class)
                .list();
    }

    @Override
    @Transactional
    public List<PhoneNumber> getPhoneNumbersOfEmployee(String empId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from PhoneNumber where employee_id='" + empId + "'", PhoneNumber.class)
                .list();
    }

    @Override
    @Transactional
    public String createPhoneNumber(PhoneNumber phoneNumber) {

        return (String) sessionFactory
                .getCurrentSession()
                .save(phoneNumber);
    }

    @Override
    @Transactional
    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        sessionFactory
                .getCurrentSession()
                .update(phoneNumber);
    }

    @Override
    @Transactional
    public boolean deletePhoneNumber(String id) {
        PhoneNumber phoneNumber = getPhoneNumber(id);

        sessionFactory.getCurrentSession().delete(phoneNumber);
        phoneNumber = sessionFactory
                .getCurrentSession()
                .find(PhoneNumber.class, id);
        return (phoneNumber == null);
    }

}
