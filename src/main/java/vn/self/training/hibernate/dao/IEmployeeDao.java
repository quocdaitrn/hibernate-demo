package vn.self.training.hibernate.dao;

import org.hibernate.Session;
import vn.self.training.hibernate.model.Employee;

import java.util.List;

public interface IEmployeeDao {
    Employee save(Session session, Employee e);

    Employee update(Session session, Employee e);

    void delete(Session session, Long id);

    Employee findByCode(Session session, String code);

    List<Employee> findByCodes(Session session, List<String> codes);

    List<Employee> queryByCodeOrFirstName(Session session, String codeOrFirstName);
}
