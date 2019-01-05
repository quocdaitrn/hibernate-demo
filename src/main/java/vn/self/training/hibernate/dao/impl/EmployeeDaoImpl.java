package vn.self.training.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import vn.self.training.hibernate.dao.IEmployeeDao;
import vn.self.training.hibernate.model.Employee;

import java.util.List;

public class EmployeeDaoImpl implements IEmployeeDao {
    @Override
    public Employee save(Session session, Employee e) {
        session.persist(e);
        return (Employee) session.get(Employee.class, e.getId());
    }

    @Override
    public Employee update(Session session, Employee e) {
        return (Employee) session.merge(e);
    }

    @Override
    public void delete(Session session, Long id) {
        Employee e = (Employee) session.get(Employee.class, id);
        session.delete(e);
    }

    @Override
    public Employee findByCode(Session session, String code) {
        Query query = session.createQuery("from Employee where code= :code");
        query.setString("code", code);

        return (Employee) query.uniqueResult();
    }

    @Override
    public List<Employee> findByCodes(Session session, List<String> codes) {
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.in("code", codes));

        return criteria.list();
    }

    @Override
    public List<Employee> queryByCodeOrFirstName(Session session, String codeOrFirstName) {
        Query query = session.createQuery("from Employee where code like CONCAT('%',:query,'%') or first_name like CONCAT('%',:query,'%')");
        query.setString("query", codeOrFirstName);

        return query.list();
    }
}
