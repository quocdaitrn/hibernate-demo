package vn.self.training.hibernate.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import vn.self.training.hibernate.dao.IEmployeeDao;
import vn.self.training.hibernate.dao.impl.EmployeeDaoImpl;
import vn.self.training.hibernate.model.Employee;
import vn.self.training.hibernate.service.IEmployeeService;
import vn.self.training.hibernate.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    private IEmployeeDao employeeDao;

    public EmployeeService() {
        employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public void addEmployee(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            employeeDao.save(session, employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void updateEmployee(Employee e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            employeeDao.update(session, e);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public void removeEmployee(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            employeeDao.delete(session, id);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            throw ex;
        } finally {
            session.close();
        }
    }

    @Override
    public Employee findByCode(String code) {
        Employee emp;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            emp = employeeDao.findByCode(session, code);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return emp;
    }

    @Override
    public List<String> queryInfoEmployee(String query) {
        List<String> sEmployees;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<Employee> employees = employeeDao.queryByCodeOrFirstName(session, query);
            sEmployees = new ArrayList<>();
            for (Employee e : employees) {
                StringBuilder s = new StringBuilder();
                s.append(e.getCode()).append(":").append(e.getFirstName()).append(" ").append(e.getLastName());
                sEmployees.add(s.toString());
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return sEmployees;
    }
}
