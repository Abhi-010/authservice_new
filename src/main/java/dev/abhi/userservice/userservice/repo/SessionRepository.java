package dev.abhi.userservice.userservice.repo;

import dev.abhi.userservice.userservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session , Long> {

    Optional<Session> findByTokenAndUserId(String token,Long userId) ;
}
