package com.web.myapp.auth;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;

    private String pass;

    private String email;

    private String nombre;

    private String apellidos;

    private String telefono;

    private Date fechaNac;
}
