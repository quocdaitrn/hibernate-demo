package vn.self.training.hibernate.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.self.training.hibernate.dto.EmployeeDto;
import vn.self.training.hibernate.model.Employee;
import vn.self.training.hibernate.response.ResponseCode;
import vn.self.training.hibernate.response.ResponseMessage;
import vn.self.training.hibernate.service.IEmployeeService;
import vn.self.training.hibernate.service.IProjectService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IProjectService projectService;

    @GetMapping(params = { "query" })
    public List<String> queryEmployeeByCodeOrFirstName(@RequestParam("query") String query) {
        return employeeService.queryInfoEmployee(query);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> addEmployee(@RequestBody EmployeeDto emp) {
        Employee e = new Employee();
        e.setCode(emp.getCode());
        e.setFirstName(emp.getFirstName());
        e.setLastName(emp.getLastName());
        e.setBirthDate(emp.getBirthDate());

        employeeService.addEmployee(e);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage(ResponseCode.SUCCESS, "Success"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto emp) {
        employeeService.updateEmployee(emp);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage(ResponseCode.SUCCESS, "Success"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.removeEmployee(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage(ResponseCode.SUCCESS, "Success"));
    }
}
