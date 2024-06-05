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
    @Query("Update Usuarios u set u.username=:username, u.email=:email, u.pass=:pass, u.nombre=:nombre, u.apellidos=:apellidos, u.telefono=:telefono, u.fechaNac=:fechaNac where u.id=:id")
    void updateUsuarios(@Param(value = "id") Long id, @Param(value = "username") String username, @Param(value = "email") String email, @Param(value = "pass") String pass, @Param(value = "nombre") String nombre, @Param(value = "apellidos") String apellidos, @Param(value = "telefono") String telefono, @Param(value = "fechaNac") Date fechaNac);

    Optional<Usuarios> findById(Long id);

}