package dev.abhi.userservice.userservice.repo;

import dev.abhi.userservice.userservice.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEmployeeRepository extends JpaRepository<Employee,Long> {
}
