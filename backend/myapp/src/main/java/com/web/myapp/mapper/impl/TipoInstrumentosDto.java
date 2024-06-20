package com.web.myapp.mapper.impl;

public class TipoInstrumentosDto {

    private Long idtipo;

    private String nombre;

    public TipoInstrumentosDto(){}

    public TipoInstrumentosDto(Long id, String nombre) {
        this.idtipo = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return idtipo;
    }

    public void setId(Long id) {
        this.idtipo = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
