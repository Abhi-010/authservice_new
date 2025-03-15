package dev.abhi.userservice.userservice.repo;

import dev.abhi.userservice.userservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
