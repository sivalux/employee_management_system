package employee_management_system.service.serviceImpl;

import employee_management_system.controller.request.DepartmentRequest;
import employee_management_system.controller.response.DepartmentResponse;
import employee_management_system.entity.Department;
import employee_management_system.repository.DepartmentRepository;
import employee_management_system.service.DepartmentService;
import employee_management_system.util.PageResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    public DepartmentServiceImpl() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true);
    }

    /**
     * service method for create department
     * @return department Id
     */
    @Override
    public Long createDepartment(DepartmentRequest request) {
        Department department = modelMapper.map(request,Department.class);
        return departmentRepository.save(department).getId();
    }

    /**
     * service method for update department
     * @param id
     * @param request
     * @return id of updated department
     */
    @Override
    public Long updateDepartment(Long id, DepartmentRequest request) {
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isEmpty()){
            throw new IllegalArgumentException("Not found any department for given departmentId : "+id);
        }
        modelMapper.map(request, department.get());
        return departmentRepository.save(department.get()).getId();
    }

    /**
     * service method for delete department
     * @param id
     * @return id of deleted department
     */
    @Override
    public Long deleteDepartment(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isEmpty()){
            throw new IllegalArgumentException("Not found any department for given departmentId : "+id);
        }
        departmentRepository.delete(department.get());
        return id;
    }

    /**
     * service method for get department by id
     * @param id
     * @return department response
     */
    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isEmpty()){
            throw new IllegalArgumentException("Not found any department for given departmentId : "+id);
        }
        return new DepartmentResponse(department.get());
    }

    /**
     * service method for get all departments
     * @param pageNo
     * @param pageSize
     * @return page response
     */
    @Override
    public PageResponse getAllDepartments(Integer pageNo, Integer pageSize) {
        Page<Department> departmentPage = departmentRepository.findAll(PageRequest.of(pageNo-1,pageSize));
        List<DepartmentResponse> departmentResponseList = departmentPage.getContent().stream()
                .map(DepartmentResponse::new)
                .collect(Collectors.toList());
        return new PageResponse(departmentResponseList,departmentPage);
    }
}
