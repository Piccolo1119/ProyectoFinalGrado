package com.web.myapp.service;

import java.util.List;
import java.util.Optional;

import com.web.myapp.model.Instrumentos;

public interface InstrumentosService {
    List<Instrumentos> findAll();
    Boolean deleteInstrumentos(Long instrumentoId);
    Instrumentos addInstrumentos(Instrumentos instrumentos);
    Instrumentos editInstrumentos(Instrumentos instrumentos);
    List<Instrumentos> findByTipoInstrumentoId(Long tipoInstrumentoId);
    Optional<Instrumentos> findInstrumentosById(Long instrumentoId);
    List<Instrumentos> getInstrumentosByVendedorId(Long idVendedor);
    List<Instrumentos> getInstrumentosNotBelongToVendedor(Long userId);
    List<Instrumentos> getInstrumentosActivos();
    void actualizarEstadoActivo(Long id, boolean estado);
    List<Instrumentos> getInstrumentosByTipoInstrumentoAndVendedorId(Long tipoInstrumento, Long vendedorId);
}
