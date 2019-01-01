package vn.self.training.hibernate.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.self.training.hibernate.dao.IEmployeeDao;
import vn.self.training.hibernate.dao.impl.EmployeeDaoImpl;
import vn.self.training.hibernate.model.Employee;
import vn.self.training.hibernate.service.IEmployeeService;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements IEmployeeService {

    private static IEmployeeDao employeeDao;

    private Session session;

    private Transaction transaction;

    public EmployeeService() {
        employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public void addEmployee(Employee employee) {
        session = employeeDao.openSession();
        transaction = null;
        try {
            transaction = session.beginTransaction();
            employeeDao.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            employeeDao.closeSession();
        }
    }

    @Override
    public Employee findByCode(String code) {
        Employee emp;

        session = employeeDao.openSession();
        transaction = null;

        try {
            transaction = session.beginTransaction();
            emp = employeeDao.findByCode(code);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            employeeDao.closeSession();
        }

        return emp;
    }

    @Override
    public List<String> queryInfoEmployee(String query) {
        List<String> sEmployees;

        session = employeeDao.openSession();
        transaction = null;

        try {
            transaction = session.beginTransaction();
            List<Employee> employees = employeeDao.queryByCodeOrFirstName(query);
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
            employeeDao.closeSession();
        }

        return sEmployees;
    }
}
