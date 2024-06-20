package com.web.myapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.myapp.model.Instrumentos;

@Repository
public interface InstrumentosRepository extends JpaRepository<Instrumentos, Long> {

    List<Instrumentos> findByTipoInstrumentoId(Long tipoInstrumentoId);

    List<Instrumentos> findByIdVendedorAndActivo(Long idVendedor, boolean activo);

    @Query("SELECT i FROM Instrumentos i WHERE i.idVendedor <> :userId AND i.activo = true")
    List<Instrumentos> findInstrumentosNotBelongToVendedor(Long userId);

    List<Instrumentos> findByActivoTrue();

    @Query("SELECT i FROM Instrumentos i WHERE i.tipoInstrumento.id = :tipoInstrumento AND i.idVendedor <> :userId AND i.activo = true")
    List<Instrumentos> findInstrumentosByTipoInstrumentoAndVendedorId(Long tipoInstrumento, Long userId);
}
