package com.web.myapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.myapp.model.Instrumentos;

public interface InstrumentosRepository extends JpaRepository<Instrumentos, Long> {

    List<Instrumentos> findByTipoInstrumentoId(Long tipoInstrumentoId);
    
}
