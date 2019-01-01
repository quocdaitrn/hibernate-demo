package vn.self.training.hibernate.service.impl;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import vn.hcmut.ap.pim.dto.ProjectDto;
//import vn.hcmut.ap.pim.persistence.model.Employee;
//import vn.hcmut.ap.pim.persistence.model.Project;
//import vn.hcmut.ap.pim.persistence.repository.IEmployeeRepository;
//import vn.hcmut.ap.pim.persistence.repository.IGroupRepository;
//import vn.hcmut.ap.pim.persistence.repository.IProjectRepository;
//import vn.hcmut.ap.pim.service.IProjectService;
//import vn.hcmut.ap.pim.util.DateTimeUtil;
//
//import javax.transaction.Transactional;
//import java.text.ParseException;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//@Transactional
//public class ProjectService implements IProjectService {
//    @Autowired
//    private IProjectRepository projectRepository;
//    @Autowired
//    private IEmployeeRepository employeeRepository;
//    @Autowired
//    private IGroupRepository groupRepository;
//
//    @Override
//    public List<Project> findAll() {
//        return projectRepository.findAll();
//    }
//
//    @Override
//    public Project findById(Long id) {
//        return projectRepository.findById(id).get();
//    }
//
//    @Override
//    public Project findByProjectNumber(int projectNumber) {
//        return projectRepository.findByProjectNumber(projectNumber).get();
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        projectRepository.deleteById(id);
//    }
//
//    @Override
//    public void updateProject(ProjectDto projectDto) throws ParseException {
//        Project proj = projectRepository.getOne(projectDto.getId());
//        // update project info
//        String endDate = setProjectInfo(projectDto, proj);
//        if (!"".equals(endDate)) {
//            proj.setEndDate(DateTimeUtil.parseStringToDate(projectDto.getEndDate()));
//        }
//        String members = projectDto.getEmployees().trim();
//        proj.setEmployees(getEmployees(members));
//
//        // save project
//        projectRepository.save(proj);
//    }
//
//    @Override
//    public void createProject(ProjectDto projectDto) throws ParseException{
//        Project newProject = new Project();
//
//        // create new project object
//        newProject.setProjectNumber(projectDto.getProjectNumber());
//        String endDate = setProjectInfo(projectDto, newProject);
//        if (!"".equals(endDate.trim())) {
//            newProject.setEndDate(DateTimeUtil.parseStringToDate(projectDto.getEndDate()));
//        }
//
//        String members = projectDto.getEmployees();
//        newProject.setEmployees(getEmployees(members));
//
//        // save new project
//        projectRepository.save(newProject);
//    }
//
//    private String setProjectInfo(ProjectDto projectDto, Project newProject) throws ParseException {
//        newProject.setName(projectDto.getName());
//        newProject.setCustomer(projectDto.getCustomer());
//        newProject.setGroup(groupRepository.findById(projectDto.getGroupId()).get());
//        newProject.setStatus(projectDto.getStatus());
//        newProject.setStartDate(DateTimeUtil.parseStringToDate(projectDto.getStartDate()));
//        return projectDto.getEndDate();
//    }
//
//    private Set<Employee> getEmployees(String sEmployees) {
//        String[] aEmployee = sEmployees.split(",");
//        return  new HashSet<>(employeeRepository.findByCodes(Arrays.asList(aEmployee)));
//    }
//}
