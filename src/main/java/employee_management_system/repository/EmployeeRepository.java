package employee_management_system.repository;

import employee_management_system.entity.Department;
import employee_management_system.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Page<Employee> findByDepartmentId(Department department, Pageable pageable);
}
