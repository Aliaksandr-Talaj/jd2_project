package it.academy.dao;

import it.academy.dao.interfaces.DepartmentDao;
import it.academy.pojos.Department;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Department> getAllDepartments() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Department", Department.class)
                .list();
    }

    @Override
    @Transactional
    public Department getDepartment(String id) {

        return sessionFactory.getCurrentSession().find(Department.class, id);
    }

    @Override
    @Transactional
    public String createDepartment(Department department) {

        return (String) sessionFactory
                .getCurrentSession()
                .save(department);
    }

    @Override
    @Transactional
    public boolean deleteDepartment(String id) {

        Department department = getDepartment(id);

        sessionFactory.getCurrentSession().delete(department);
        department = sessionFactory
                .getCurrentSession()
                .find(Department.class, id);
        return (department == null);

    }

    @Override
    @Transactional
    public void updateDepartment(Department department) {
        sessionFactory.getCurrentSession().update(department);
    }
}
