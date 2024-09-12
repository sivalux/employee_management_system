package employee_management_system.service.serviceImpl;

import employee_management_system.controller.request.EmployeeRequest;
import employee_management_system.controller.response.EmployeeResponse;
import employee_management_system.entity.Department;
import employee_management_system.entity.Employee;
import employee_management_system.repository.DepartmentRepository;
import employee_management_system.repository.EmployeeRepository;
import employee_management_system.service.EmployeeService;
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
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    public EmployeeServiceImpl() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true);
    }

    /**
     * service method for create employee
     * @return employee Id
     */
    @Override
    public Long createEmployee(EmployeeRequest request) {
        Optional<Department> department = departmentRepository.findById(request.getDepartmentId());
        if(department.isEmpty()){
            throw new IllegalArgumentException("Not found any department for given departmentId : "+request.getDepartmentId());
        }
        Employee employee = modelMapper.map(request,Employee.class);
        employee.setDepartmentId(department.get());
        return employeeRepository.save(employee).getId();
    }

    /**
     * service method for update employee
     * @param id
     * @param request
     * @return id of updated employee
     */
    @Override
    public Long updateEmployee(Long id, EmployeeRequest request) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new IllegalArgumentException("Not found any employee for given employeeId : "+id);
        }
        Optional<Department> department = departmentRepository.findById(request.getDepartmentId());
        if(department.isEmpty()){
            throw new IllegalArgumentException("Not found any department for given departmentId : "+request.getDepartmentId());
        }
        modelMapper.map(request, employee.get());
        employee.get().setDepartmentId(department.get());
        return employeeRepository.save(employee.get()).getId();
    }

    /**
     * service method for delete employee
     * @param id
     * @return id of deleted employee
     */
    @Override
    public Long deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new IllegalArgumentException("Not found any employee for given employeeId : "+id);
        }
        employeeRepository.delete(employee.get());
        return id;
    }

    /**
     * service method for get employee by id
     * @param id
     * @return employee response
     */
    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new IllegalArgumentException("Not found any employee for given employeeId : "+id);
        }
        return new EmployeeResponse(employee.get());
    }

    @Override
    public PageResponse getAllEmployees(Integer pageNo, Integer pageSize, Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(department.isEmpty()){
            throw new IllegalArgumentException("Not found any department for given departmentId : "+departmentId);
        }
        Page<Employee> employeePage = employeeRepository.findByDepartmentId(department.get(),PageRequest.of(pageNo-1,pageSize));
        List<EmployeeResponse> employeeResponseList = employeePage.getContent().stream()
                .map(EmployeeResponse::new)
                .collect(Collectors.toList());
        return new PageResponse(employeeResponseList,employeePage);
    }


}
