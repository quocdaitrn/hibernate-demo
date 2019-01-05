package vn.self.training.hibernate.service;

import vn.self.training.hibernate.model.Groupe;

import java.util.List;

public interface IGroupService {
    List<Groupe> findAll();

    Groupe findById(Long id);

    Groupe save(Groupe groupe);

    void delete(Long id);
}
