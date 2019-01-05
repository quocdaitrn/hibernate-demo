package vn.self.training.hibernate.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.self.training.hibernate.dto.ProjectDto;
import vn.self.training.hibernate.model.Project;
import vn.self.training.hibernate.response.ResponseCode;
import vn.self.training.hibernate.response.ResponseMessage;
import vn.self.training.hibernate.service.IProjectService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private static Logger logger = Logger.getLogger(ProjectController.class);

    @Autowired
    private IProjectService projectService;

    @GetMapping
    List<Project> query() {
        return projectService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
        projectService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage(ResponseCode.SUCCESS, "Success"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> edit(@PathVariable("id") Long id, @RequestBody ProjectDto dto) {
        try {
            projectService.updateProject(dto);
        } catch (ParseException e) {
            logger.error("Parse date error : " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(ResponseCode.PARSER_EXCEPTION, e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage(ResponseCode.SUCCESS, "Success"));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> create(@RequestBody ProjectDto dto) {
        try {
            projectService.createProject(dto);
        } catch (ParseException e) {
            logger.error("Parse date error : " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(ResponseCode.PARSER_EXCEPTION, e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseMessage(ResponseCode.SUCCESS, "Success"));
    }
}
