package com.bootcampstorageult.bootcampstorageult.repository;

import com.bootcampstorageult.bootcampstorageult.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByUsername(String username);
}
