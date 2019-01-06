package vn.self.training.hibernate.service;

import vn.self.training.hibernate.dto.EmployeeDto;
import vn.self.training.hibernate.model.Employee;

import java.util.List;

public interface IEmployeeService {
    void addEmployee(Employee e);

    void updateEmployee(EmployeeDto e);

    void removeEmployee(Long id);

    Employee findById(Long id);

    Employee findByCode(String code);

    List<String> queryInfoEmployee(String query);
}
