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
    private String monto;

    @Column(name = "id_comprador")
    private Long comprador;

    @ManyToOne
    @JoinColumn(name = "id_instrumento", referencedColumnName = "id")
    private Instrumentos instrumento;

    @Column(name = "cod_pedido")
    private String codigo;

}
