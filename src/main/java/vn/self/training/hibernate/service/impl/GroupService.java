package vn.self.training.hibernate.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import vn.self.training.hibernate.dao.IGroupeDao;
import vn.self.training.hibernate.dao.impl.GroupeDaoImpl;
import vn.self.training.hibernate.model.Groupe;
import vn.self.training.hibernate.service.IGroupService;
import vn.self.training.hibernate.util.HibernateUtil;

import java.util.List;

@Service
public class GroupService implements IGroupService {

    private IGroupeDao groupeDao;

    public GroupService() {
        groupeDao = new GroupeDaoImpl();
    }

    @Override
    public List<Groupe> findAll() {
        List<Groupe> groups;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            groups = groupeDao.findAll(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return groups;
    }

    @Override
    public Groupe findById(Long id) {
        Groupe group;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            group = groupeDao.findById(session, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return group;
    }

    @Override
    public Groupe save(Groupe groupe) {
        Groupe group;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            group = groupeDao.save(session, groupe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return group;
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            groupeDao.deleteById(session, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
