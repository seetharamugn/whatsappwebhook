package com.example.webhook.repository;

import com.example.webhook.dao.ERole;
import com.example.webhook.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}