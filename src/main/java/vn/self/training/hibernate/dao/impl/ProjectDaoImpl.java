package vn.self.training.hibernate.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import vn.self.training.hibernate.dao.IEmployeeDao;
import vn.self.training.hibernate.dao.IGroupeDao;
import vn.self.training.hibernate.dao.IProjectDao;
import vn.self.training.hibernate.dto.ProjectDto;
import vn.self.training.hibernate.model.Employee;
import vn.self.training.hibernate.model.Project;
import vn.self.training.hibernate.util.DateTimeUtil;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectDaoImpl implements IProjectDao {
    private IEmployeeDao employeeDao;
    private IGroupeDao groupeDao;

    public ProjectDaoImpl() {
        employeeDao = new EmployeeDaoImpl();
        groupeDao = new GroupeDaoImpl();
    }

    @Override
    public List<Project> findAll(Session session) {
        return session.createCriteria(Project.class).list();
    }

    @Override
    public Project findById(Session session, Long id) {
        return (Project) session.get(Project.class, id);
    }

    @Override
    public List<Project> findByIds(Session session, List<Long> ids) {
        Criteria cr = session.createCriteria(Project.class);
        cr.add(Restrictions.in("id", ids));
        return cr.list();
    }

    @Override
    public Project findByProjectNumber(Session session, int projectNumber) {
        Criteria cr = session.createCriteria(Project.class);
        cr.add(Restrictions.eq("projectNumber", projectNumber));

        return (Project) cr.uniqueResult();
    }

    @Override
    public void updateProject(Session session, ProjectDto projectDto) throws ParseException {
        Project proj = (Project) session.get(Project.class, projectDto.getId());
        // update project info
        String endDate = setProjectInfo(session, projectDto, proj);
        if (!"".equals(endDate)) {
            proj.setEndDate(DateTimeUtil.parseStringToDate(projectDto.getEndDate()));
        }
        String members = projectDto.getEmployees().trim();
        proj.setEmployees(getEmployees(session, members));

        // merge project
        session.merge(proj);
    }

    @Override
    public void createProject(Session session, ProjectDto projectDto) throws ParseException {
        Project newProject = new Project();

        // create new project object
        newProject.setProjectNumber(projectDto.getProjectNumber());
        String endDate = setProjectInfo(session, projectDto, newProject);
        if (!"".equals(endDate.trim())) {
            newProject.setEndDate(DateTimeUtil.parseStringToDate(projectDto.getEndDate()));
        }

        String members = projectDto.getEmployees();
        newProject.setEmployees(getEmployees(session, members));

        // save new project
        session.persist(newProject);
    }

    @Override
    public void deleteById(Session session, Long id) {
        Project project = findById(session, id);
        session.delete(project);
    }

    private String setProjectInfo(Session session, ProjectDto projectDto, Project persistProject) throws ParseException {
        persistProject.setName(projectDto.getName());
        persistProject.setCustomer(projectDto.getCustomer());
        persistProject.setGroup(groupeDao.findById(session, projectDto.getGroupId()));
        persistProject.setStatus(projectDto.getStatus());
        persistProject.setStartDate(DateTimeUtil.parseStringToDate(projectDto.getStartDate()));
        return projectDto.getEndDate();
    }

    private Set<Employee> getEmployees(Session session, String sEmployees) {
        String[] aEmployee = sEmployees.split(",");
        return  new HashSet<>(employeeDao.findByCodes(session, Arrays.asList(aEmployee)));
    }
}
