package it.academy.dao;

import it.academy.dao.interfaces.EmployeeDao;
import it.academy.pojos.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional()
    public List<Employee> getAllEmployeesInDepartment(String departmentId) {
//        String query = "select * from Employee where department_id='" + departmentId + "';";
//        List list = sessionFactory
//                .getCurrentSession()
//                .createSQLQuery(query).list();
//        return list;

        String query = "from Employee where department_id='" + departmentId + "'";
        return sessionFactory
                .getCurrentSession().createQuery(query, Employee.class)
                .list();
    }

    @Override
    @Transactional
    public Employee getEmployee(String id) {
        return sessionFactory
                .getCurrentSession()
                .find(Employee.class, id);
    }

    @Override
    @Transactional
    public String createEmployee(Employee employee) {

        return (String) sessionFactory
                .getCurrentSession()
                .save(employee);
    }

    @Override
    @Transactional
    public boolean deleteEmployee(String id) {

        Employee employee = sessionFactory
                .getCurrentSession()
                .find(Employee.class, id);
        sessionFactory.getCurrentSession().delete(employee);
        employee = sessionFactory
                .getCurrentSession()
                .find(Employee.class, id);
        return (employee == null);
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employee) {
        sessionFactory.getCurrentSession().saveOrUpdate(employee);
    }

}
