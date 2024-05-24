package com.web.myapp.service;

import java.util.List;
import com.web.myapp.model.TipoInstrumentos;

public interface TipoInstrumentosService {
    List<TipoInstrumentos> findAll();
    Boolean deleteTipoInstrumentos(Long tipoInstrumentoId);
    TipoInstrumentos addTipoInstrumentos(TipoInstrumentos tipoInstrumentos);
    TipoInstrumentos editTipoInstrumentos(TipoInstrumentos tipoInstrumentos);

}
