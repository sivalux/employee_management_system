package employee_management_system.controller.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeRequest {

    @NotNull(message = "Department Id cannot be null.")
    private Long departmentId;

    @NotEmpty(message = "First Name cannot be empty.")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty.")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Phone No cannot be empty.")
    private String phone;
}
