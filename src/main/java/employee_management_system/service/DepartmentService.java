package employee_management_system.service;

import employee_management_system.controller.request.DepartmentRequest;
import employee_management_system.controller.response.DepartmentResponse;
import employee_management_system.util.PageResponse;

public interface DepartmentService {

    /**
     * service method for create department
     * @return department Id
     */
    Long createDepartment(final DepartmentRequest request);

    /**
     * service method for update department
     * @param id
     * @param request
     * @return id of updated department
     */
    Long updateDepartment(final Long id, final DepartmentRequest request);

    /**
     * service method for delete department
     * @param id
     * @return id of deleted department
     */
    Long deleteDepartment(final Long id);

    /**
     * service method for get department by id
     * @param id
     * @return department response
     */
    DepartmentResponse getDepartmentById(final Long id);

    /**
     * service method for get all departments
     * @param pageNo
     * @param pageSize
     * @return page response
     */
    PageResponse getAllDepartments(final Integer pageNo, final Integer pageSize);
}
