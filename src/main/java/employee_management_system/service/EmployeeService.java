package employee_management_system.service;

import employee_management_system.controller.request.EmployeeRequest;
import employee_management_system.controller.response.EmployeeResponse;
import employee_management_system.util.PageResponse;

public interface EmployeeService {

    /**
     * service method for create employee
     * @return employee Id
     */
    Long createEmployee(final EmployeeRequest request);

    /**
     * service method for update employee
     * @param id
     * @param request
     * @return id of updated employee
     */
    Long updateEmployee(final Long id, final EmployeeRequest request);

    /**
     * service method for delete employee
     * @param id
     * @return id of deleted employee
     */
    Long deleteEmployee(final Long id);

    /**
     * service method for get employee by id
     * @param id
     * @return employee response
     */
    EmployeeResponse getEmployeeById(final Long id);

    /**
     * service method for get all employees by given department id
     * @param pageNo
     * @param pageSize
     * @param departmentId
     * @return page response
     */
    PageResponse getAllEmployees(final Integer pageNo, final Integer pageSize, final  Long departmentId);
}
