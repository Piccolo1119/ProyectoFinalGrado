package com.web.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.myapp.model.Usuarios;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByUsername(String username);

    @Modifying()
    @Query("update Usuarios u set u.nombre = :nombre, u.apellidos = :apellidos, u.email = :email, u.username = :username, u.telefono = :telefono, u.fechaNac = :fechaNac where u.id = :id")
    void updateUsuarios(@Param(value = "id") Long id, @Param(value = "nombre") String nombre, @Param(value = "apellidos") String apellidos, @Param(value = "email") String email, @Param(value = "username") String username, @Param(value = "telefono") String telefono, @Param(value = "fechaNac") Date fechaNac);
    Optional<Usuarios> findById(Long id);

    Optional<Usuarios> findByEmail(String email);

}