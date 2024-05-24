package com.web.myapp.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_instrumentos")
public class TipoInstrumentos {
    
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true)
    private Long id;

    @Column(length = 255)
    private String nombre;
}
