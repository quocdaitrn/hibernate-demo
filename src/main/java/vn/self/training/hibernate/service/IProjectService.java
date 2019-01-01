package vn.self.training.hibernate.service;

import vn.self.training.hibernate.dto.ProjectDto;
import vn.self.training.hibernate.model.Project;

import java.text.ParseException;
import java.util.List;

public interface IProjectService {
    List<Project> findAll();

    Project findById(Long id);

    Project findByProjectNumber(int projectNumber);

    void updateProject(ProjectDto projectDto) throws ParseException;

    void createProject(ProjectDto projectDto) throws ParseException;

    void deleteById(Long id);
}
