package vn.self.training.hibernate.dao;

import org.hibernate.Session;
import vn.self.training.hibernate.model.Groupe;

import java.util.List;

public interface IGroupeDao {
    List<Groupe> findAll(Session session);

    Groupe findById(Session session, Long id);

    Groupe save(Session session, Groupe groupe);

    void deleteById(Session session, Long id);
}
