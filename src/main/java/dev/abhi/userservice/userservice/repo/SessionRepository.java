package dev.abhi.userservice.userservice.repo;

import dev.abhi.userservice.userservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session , Long> {
}
