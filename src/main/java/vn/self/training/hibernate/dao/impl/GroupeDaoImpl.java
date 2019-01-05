package vn.self.training.hibernate.dao.impl;

import org.hibernate.Session;
import vn.self.training.hibernate.dao.IGroupeDao;
import vn.self.training.hibernate.model.Groupe;

import java.util.List;

public class GroupeDaoImpl implements IGroupeDao {
    @Override
    public List<Groupe> findAll(Session session) {
        return session.createCriteria(Groupe.class).list();
    }

    @Override
    public Groupe findById(Session session, Long id) {
        return (Groupe) session.get(Groupe.class, id);
    }

    @Override
    public Groupe save(Session session, Groupe groupe) {
        session.persist(groupe);
        return (Groupe) session.get(Groupe.class, groupe.getId());
    }

    @Override
    public void deleteById(Session session, Long id) {
        Groupe groupe = findById(session, id);
        session.delete(groupe);
    }
}
