package employee_management_system.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DepartmentRequest {

    @NotEmpty(message = "Department Name cannot be empty.")
    private String name;
}
