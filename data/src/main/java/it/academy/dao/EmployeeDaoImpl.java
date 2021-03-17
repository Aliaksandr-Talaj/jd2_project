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
        String query;

        if (departmentId == null) {
            query = "select * from Employee where employee.department_id is null;";
            return (List<Employee>) sessionFactory
                    .getCurrentSession()
                    .createSQLQuery(query)
                    .list();
        } else {
            query = "from Employee e left join fetch e.positions where e.department.id='" + departmentId + "'";
        }
        return sessionFactory
                .getCurrentSession()
                .createQuery(query, Employee.class)
                .list();
    }

    @Override
    @Transactional
    public Employee getEmployee(String id) {
        String query = "select e from Employee e left join fetch e.positions where e.id='" + id + "'";
        return sessionFactory
                .getCurrentSession()
                .createQuery(query, Employee.class)
                .uniqueResult();

    }

    @Override
    @Transactional
    public String getDepartmentIdOfEmployee(String empId) {
        String SqlQuery = "select department.id from Employee e where e.id='" + empId + "';";
        List<String> stringList = (List<String>) sessionFactory.getCurrentSession().createSQLQuery(SqlQuery).list();
        if (stringList.size() != 0) {
            return stringList.get(0);
        }
        return "";
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
        sessionFactory.getCurrentSession().update(employee);
    }

}
