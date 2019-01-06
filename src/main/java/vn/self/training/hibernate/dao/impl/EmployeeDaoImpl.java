package vn.self.training.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import vn.self.training.hibernate.dao.IEmployeeDao;
import vn.self.training.hibernate.dto.EmployeeDto;
import vn.self.training.hibernate.model.Employee;
import vn.self.training.hibernate.model.Project;

import java.util.HashSet;
import java.util.List;

public class EmployeeDaoImpl implements IEmployeeDao {
    @Override
    public Employee save(Session session, Employee e) {
        session.persist(e);
        return (Employee) session.get(Employee.class, e.getId());
    }

    @Override
    public Employee update(Session session, EmployeeDto emp) {
        Employee e = findById(session, emp.getId());
        e.setFirstName(emp.getFirstName());
        e.setLastName(emp.getLastName());
        e.setBirthDate(emp.getBirthDate());

        Criteria cr = session.createCriteria(Project.class);
        cr.add(Restrictions.in("id", emp.getProjects()));
        List<Project> ps = cr.list();

        e.setProjects(new HashSet<>(ps));

        return (Employee) session.merge(e);
    }

    @Override
    public void delete(Session session, Long id) {
        Employee e = (Employee) session.get(Employee.class, id);
        session.delete(e);
    }

    @Override
    public Employee findById(Session session, Long id) {
        return (Employee) session.get(Employee.class, id);
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
