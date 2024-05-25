package com.web.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.web.myapp.model.Usuarios;
import java.util.List;
import java.util.Optional;


public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByUsername(String username);
}