package com.web.myapp.repository;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.myapp.model.TipoInstrumentos;

@Repository
public interface TipoInstrumentosRepository extends JpaRepository<TipoInstrumentos, Long> {

}