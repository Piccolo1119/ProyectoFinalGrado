package com.web.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.web.myapp.model.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    
}