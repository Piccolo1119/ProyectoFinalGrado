package com.web.myapp.model;

import java.sql.Date;

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
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "estado_pago")
    private String estadoPago;

    @Column(name = "fecha_pedido")
    private Date fecha;

    @Column(name = "monto")
    private Double monto;

    @ManyToOne
    @JoinColumn(name = "id_comprador", referencedColumnName = "id")
    private Usuarios comprador;

    @ManyToOne
    @JoinColumn(name = "id_instrumento", referencedColumnName = "id")
    private Instrumentos instrumento;

    @ManyToOne
    @JoinColumn(name = "telefono_vendedor", referencedColumnName = "telefono")
    private Usuarios telefonoVendedor;


    
}
