package employee_management_system.controller.response;

import employee_management_system.entity.Employee;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeResponse {

    private Long id;

    private Long departmentId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    public EmployeeResponse(Employee employee){
        setId(employee.getId());
        setDepartmentId(employee.getDepartmentId().getId());
        setFirstName(employee.getFirstName());
        setLastName(employee.getLastName());
        setEmail(employee.getEmail());
        setPhone(employee.getPhone());
    }
}
