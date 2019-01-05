package vn.self.training.hibernate.service.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import vn.self.training.hibernate.dao.IProjectDao;
import vn.self.training.hibernate.dao.impl.ProjectDaoImpl;
import vn.self.training.hibernate.dto.ProjectDto;
import vn.self.training.hibernate.model.Project;
import vn.self.training.hibernate.service.IProjectService;
import vn.self.training.hibernate.util.HibernateUtil;

import java.text.ParseException;
import java.util.List;

@Service
public class ProjectService implements IProjectService {
    private IProjectDao projectDao;

    public ProjectService() {
        projectDao = new ProjectDaoImpl();
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            projects = projectDao.findAll(session);
            for (Project p : projects) {
                Hibernate.initialize(p.getEmployees());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return projects;
    }

    @Override
    public Project findById(Long id) {
        Project project;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            project = projectDao.findById(session, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return project;
    }

    @Override
    public List<Project> findByIds(List<Long> ids) {
        List<Project> projects;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            projects = projectDao.findByIds(session, ids);
            for (Project p : projects) {
                Hibernate.initialize(p.getEmployees());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return projects;
    }

    @Override
    public Project findByProjectNumber(int projectNumber) {
        Project project;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            project = projectDao.findByProjectNumber(session, projectNumber);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return project;
    }

    @Override
    public void updateProject(ProjectDto projectDto) throws ParseException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            projectDao.updateProject(session, projectDto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void createProject(ProjectDto projectDto) throws ParseException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            projectDao.createProject(session, projectDto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            projectDao.deleteById(session, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
