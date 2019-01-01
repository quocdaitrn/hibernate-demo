package vn.self.training.hibernate;

import org.hibernate.Session;
import vn.self.training.hibernate.model.Employee;
import vn.self.training.hibernate.service.IEmployeeService;
import vn.self.training.hibernate.service.impl.EmployeeService;
import vn.self.training.hibernate.util.HibernateUtil;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        IEmployeeService service = new EmployeeService();

        Employee e = new Employee();
        e.setCode("TDN");
        e.setFirstName("Dung");
        e.setLastName("Nguyen");
        e.setBirthDate(Date.from(LocalDate.of(1994, 05, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        service.addEmployee(e);
//
//        Employee emp = service.findByCode("QDT");
//
//        System.out.println(emp.getFirstName());
//
//        List<String> emps = service.queryInfoEmployee("D");
//        System.out.println(emps);

        Session session = HibernateUtil.getSessionFactory().openSession();

    }
}
