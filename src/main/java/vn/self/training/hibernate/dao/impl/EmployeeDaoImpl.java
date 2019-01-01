package vn.self.training.hibernate.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.self.training.hibernate.dao.IEmployeeDao;
import vn.self.training.hibernate.model.Employee;
import vn.self.training.hibernate.util.HibernateUtil;

import java.util.List;

public class EmployeeDaoImpl implements IEmployeeDao {

    private Session session;

    @Override
    public Session openSession() {
        session = HibernateUtil.getSessionFactory().openSession();

        return session;
    }

    @Override
    public void closeSession() {
        session.close();
    }

    @Override
    public Employee save(Employee e) {
        session.persist(e);
        return (Employee) session.get(Employee.class, e.getId());
    }

    @Override
    public Employee findByCode(String code) {
        Query query = session.createQuery("from Employee where code= :code");
        query.setString("code", code);
        Employee emp = (Employee) query.uniqueResult();

        return emp;
    }

    @Override
    public List<Employee> queryByCodeOrFirstName(String codeOrFirstName) {
        Query query = session.createQuery("from Employee where code like CONCAT('%',:query,'%') or first_name like CONCAT('%',:query,'%')");
        query.setString("query", codeOrFirstName);
        List<Employee> emps = query.list();

        return emps;
    }
}
