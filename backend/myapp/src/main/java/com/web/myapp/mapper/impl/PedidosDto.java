package com.web.myapp.mapper.impl;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidosDto {

    private Long id;
    
    private String estadoPago;
    
    private Date fecha;
    
    private String monto;
    
    private Long idComprador;
    
    private Long idInstrumento;

}
