package com.web.myapp.service;

import java.util.List;

import com.web.myapp.model.Instrumentos;

public interface InstrumentosService {
    List<Instrumentos> findAll();
    Boolean deleteInstrumentos(Long instrumentoId);
    Instrumentos addInstrumentos(Instrumentos instrumentos);
    Instrumentos editInstrumentos(Instrumentos instrumentos);
    List<Instrumentos> findByTipoInstrumentoId(Long tipoInstrumentoId);
}
