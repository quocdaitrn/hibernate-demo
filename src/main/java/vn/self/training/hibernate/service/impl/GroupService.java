package vn.self.training.hibernate.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import vn.hcmut.ap.pim.persistence.model.Groupe;
//import vn.hcmut.ap.pim.persistence.repository.IGroupRepository;
//import vn.hcmut.ap.pim.service.IGroupService;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Service
//@Transactional
//public class GroupService implements IGroupService {
//    @Autowired
//    private IGroupRepository groupRepository;
//
//    @Override
//    public List<Groupe> findAll() {
//        return groupRepository.findAll();
//    }
//
//    @Override
//    public Groupe findById(Long id) {
//        return groupRepository.findById(id).get();
//    }
//
//    @Override
//    public Groupe save(Groupe groupe) {
//        return groupRepository.save(groupe);
//    }
//
//    @Override
//    public void delete(Long id) {
//        groupRepository.deleteById(id);
//    }
//}
