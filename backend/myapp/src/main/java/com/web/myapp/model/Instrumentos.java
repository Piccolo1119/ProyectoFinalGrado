package com.web.myapp.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "instrumentos")
public class Instrumentos {
    
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id", unique = true)
    private Long id;

    @Column(length = 255)
    private String nombre;

    @Column(length = 255)
    private String marca;

    @Column(name = "precio")
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "tipo_instrumento", referencedColumnName = "id")
    private TipoInstrumentos tipoInstrumento;

    @Column(length = 255)
    private String imagen;

    @Column(name = "id_vendedor")
    private Long idVendedor;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "activo")
    private boolean activo;
    
    
}
