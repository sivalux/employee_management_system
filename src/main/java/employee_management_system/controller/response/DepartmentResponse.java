package employee_management_system.controller.response;

import employee_management_system.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DepartmentResponse {

    private Long id;

    private String name;

    public DepartmentResponse(Department department){
        setId(department.getId());
        setName(department.getName());
    }
}
