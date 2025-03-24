package com.godoc.service.credentials.repository;

import com.godoc.service.credentials.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    Credentials findByUsername(String username);
}
