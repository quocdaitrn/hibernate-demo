package vn.self.training.hibernate.dao;

import org.hibernate.Session;
import vn.self.training.hibernate.model.Employee;

import java.util.List;

public interface IEmployeeDao {
    Session openSession();

    void closeSession();

    Employee save(Employee e);

    Employee findByCode(String code);

    List<Employee> queryByCodeOrFirstName(String codeOrFirstName);
}
