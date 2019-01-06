package vn.self.training.hibernate.dao;

import org.hibernate.Session;
import vn.self.training.hibernate.dto.EmployeeDto;
import vn.self.training.hibernate.model.Employee;

import java.util.List;

public interface IEmployeeDao {
    Employee save(Session session, Employee e);

    Employee update(Session session, EmployeeDto e);

    void delete(Session session, Long id);

    Employee findById(Session session, Long id);

    Employee findByCode(Session session, String code);

    List<Employee> findByCodes(Session session, List<String> codes);

    List<Employee> queryByCodeOrFirstName(Session session, String codeOrFirstName);
}
