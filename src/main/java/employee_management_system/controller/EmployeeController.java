package employee_management_system.controller;

import employee_management_system.controller.request.EmployeeRequest;
import employee_management_system.controller.response.EmployeeResponse;
import employee_management_system.service.EmployeeService;
import employee_management_system.util.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/employees")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Create employee")
    @ApiResponse(responseCode = "201", description = "Employee created successfully...", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class))})
    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        Long response = employeeService.createEmployee(request);
        LOGGER.info("Employee created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update employee")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully...", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class))})
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeRequest request) {
        Long response = employeeService.updateEmployee(id, request);
        LOGGER.info("Employee updated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete employee")
    @ApiResponse(responseCode = "200", description = "Employee deleted successfully...", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class))})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        Long response = employeeService.deleteEmployee(id);
        LOGGER.info("Employee deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get employee by id")
    @ApiResponse(responseCode = "200", description = "Employee retrieved successfully...", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeResponse.class))})
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse response = employeeService.getEmployeeById(id);
        LOGGER.info("Employee retrieved successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get all employees by department Id")
    @ApiResponse(responseCode = "200", description = "Employees retrieved successfully...", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PageResponse.class))})
    @GetMapping
    public ResponseEntity<?> getAllEmployees( @RequestParam(value = "pageNo", required = true) Integer pageNo,
                                          @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                              @RequestParam(value = "departmentId", required = true) Long departmentId){
        PageResponse response = employeeService.getAllEmployees(pageNo,pageSize,departmentId);
        LOGGER.info("Employees retrieved successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
