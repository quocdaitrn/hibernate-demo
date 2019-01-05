package vn.self.training.hibernate.dao;

import org.hibernate.Session;
import vn.self.training.hibernate.dto.ProjectDto;
import vn.self.training.hibernate.model.Project;

import java.text.ParseException;
import java.util.List;

public interface IProjectDao {
    List<Project> findAll(Session session);

    Project findById(Session session, Long id);

    List<Project> findByIds(Session session, List<Long> ids);

    Project findByProjectNumber(Session session, int projectNumber);

    void updateProject(Session session, ProjectDto projectDto) throws ParseException;

    void createProject(Session session, ProjectDto projectDto) throws ParseException;

    void deleteById(Session session, Long id);
}
