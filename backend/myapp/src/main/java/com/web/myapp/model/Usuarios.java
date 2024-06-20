package com.web.myapp.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class Usuarios implements UserDetails {

    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id", unique = true, nullable = false)
    Long id;

    @Column(name = "nombre", length = 100)
    String nombre;

    @Column(name = "apellidos", length = 100 )
    String apellidos;

    @Column(name = "email", length = 255)
    String email;

    @Column(name = "username", length = 255, nullable = false)
    String username;

    @Column(name = "pass", length = 255)
    String pass;

    @Column(name = "telefono")
    String telefono;

    @Column(name = "fecha_nacimiento")
    Date fechaNac;

    @Column(name = "rol", length = 50)
    @Enumerated(EnumType.STRING)
    Rol rol;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.pass;
    }

}
