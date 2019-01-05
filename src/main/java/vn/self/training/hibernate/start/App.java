package vn.self.training.hibernate.start;

import vn.self.training.hibernate.model.Employee;
import vn.self.training.hibernate.service.IEmployeeService;
import vn.self.training.hibernate.service.impl.EmployeeService;

import java.util.List;

public class App {
    public static void main(String[] args) {
        IEmployeeService service = new EmployeeService();

        List<String> employees = service.queryInfoEmployee("QDT");

        System.out.println(employees);
    }
}
