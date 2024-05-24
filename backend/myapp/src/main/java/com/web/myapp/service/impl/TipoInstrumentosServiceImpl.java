package com.web.myapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.myapp.repository.TipoInstrumentosRepository;
import com.web.myapp.service.TipoInstrumentosService;
import com.web.myapp.mapper.impl.TipoInstrumentosDto;
import com.web.myapp.model.TipoInstrumentos;


@Service
public class TipoInstrumentosServiceImpl implements TipoInstrumentosService{

    @Autowired
    private TipoInstrumentosRepository daoTipoInstrumento;
    @Override
    public List<TipoInstrumentos> findAll() {
        List<TipoInstrumentos> listTipoInstrumentos = daoTipoInstrumento.findAll();
        return listTipoInstrumentos;
    }

    @Override
    public Boolean deleteTipoInstrumentos(Long tipoInstrumentoId) {
        Optional<TipoInstrumentos> tipoInstrumento = daoTipoInstrumento.findById(tipoInstrumentoId);
        if (!tipoInstrumento.isPresent()) {
            return false;
        }
        daoTipoInstrumento.delete(tipoInstrumento.get());
        return true;
    }

    @Override
    public TipoInstrumentos addTipoInstrumentos(TipoInstrumentos tipoInstrumento){
        return daoTipoInstrumento.save(tipoInstrumento);
    }

    @Override
    public TipoInstrumentos editTipoInstrumentos(TipoInstrumentos tipoInstrumento) {
        return daoTipoInstrumento.save(tipoInstrumento);
    }

}
