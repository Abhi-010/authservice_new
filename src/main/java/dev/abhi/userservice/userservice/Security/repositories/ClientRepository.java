package dev.abhi.userservice.userservice.Security.repositories;

import java.util.Optional;

import dev.abhi.userservice.userservice.Security.models.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
	Optional<Client> findByClientId(String clientId);
}