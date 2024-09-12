package employee_management_system.controller;

import employee_management_system.controller.request.DepartmentRequest;
import employee_management_system.controller.response.DepartmentResponse;
import employee_management_system.service.DepartmentService;
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
@RequestMapping(path = "/api/v1/departments")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "Create department")
    @ApiResponse(responseCode = "201", description = "Department created successfully...", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class))})
    @PostMapping
    public ResponseEntity<?> createDepartment(@Valid @RequestBody DepartmentRequest request) {
        Long response = departmentService.createDepartment(request);
        LOGGER.info("Department created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update department")
    @ApiResponse(responseCode = "200", description = "Department updated successfully...", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class))})
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long id,@Valid @RequestBody DepartmentRequest request) {
        Long response = departmentService.updateDepartment(id, request);
        LOGGER.info("Department updated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete department")
    @ApiResponse(responseCode = "200", description = "Department deleted successfully...", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class))})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        Long response = departmentService.deleteDepartment(id);
        LOGGER.info("Department deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get department by id")
    @ApiResponse(responseCode = "200", description = "Department retrieved successfully...", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = DepartmentResponse.class))})
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id) {
        DepartmentResponse response = departmentService.getDepartmentById(id);
        LOGGER.info("Department retrieved successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get all departments")
    @ApiResponse(responseCode = "200", description = "Departments retrieved successfully...", content = {
            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PageResponse.class))})
    @GetMapping
    public ResponseEntity<?> getAllDepartments( @RequestParam(value = "pageNo", required = true) Integer pageNo,
                                              @RequestParam(value = "pageSize", required = true) Integer pageSize){
        PageResponse response = departmentService.getAllDepartments(pageNo,pageSize);
        LOGGER.info("Departments retrieved successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
