package vn.self.training.hibernate.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.self.training.hibernate.dto.GroupDto;
import vn.self.training.hibernate.model.Groupe;
import vn.self.training.hibernate.service.IEmployeeService;
import vn.self.training.hibernate.service.IGroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private IGroupService groupService;

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/groups")
    List<Groupe> query() {
        return groupService.findAll();
    }

    @PostMapping("/groups")
    Groupe create(@RequestBody GroupDto group) {
        Groupe groupe = new Groupe();
        groupe.setGroupLeader(employeeService.findByCode(group.getGroupLeader()));

        return groupService.save(groupe);
    }
}
