package com.web.myapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.myapp.model.Instrumentos;
import com.web.myapp.repository.InstrumentosRepository;
import com.web.myapp.service.InstrumentosService;

@Service
public class InstrumentosServiceImpl implements InstrumentosService {

    @Autowired
    private InstrumentosRepository daoInstrumentos;

    @Override
    public List<Instrumentos> findAll() {
        List<Instrumentos> listInstrumentos = daoInstrumentos.findAll();
        return listInstrumentos;
    }

    @Override
    public Boolean deleteInstrumentos(Long instrumentoId) {
        Optional<Instrumentos> instrumento = daoInstrumentos.findById(instrumentoId);
        if (!instrumento.isPresent()) {
            return false;
        }
        daoInstrumentos.delete(instrumento.get());
        return true;
    }

    @Override
    public Instrumentos addInstrumentos(Instrumentos instrumentos) {
        return daoInstrumentos.save(instrumentos);
    }

    @Override
    public Instrumentos editInstrumentos(Instrumentos instrumentos) {
        return daoInstrumentos.save(instrumentos);
    }

    public List<Instrumentos> findByTipoInstrumentoId(Long tipoInstrumentoId) {
        return daoInstrumentos.findByTipoInstrumentoId(tipoInstrumentoId);
    }

    @Override
    public Optional<Instrumentos> findInstrumentosById(Long instrumentoId) {
        return daoInstrumentos.findById(instrumentoId);
    }

    public List<Instrumentos> getInstrumentosByVendedorId(Long idVendedor) {
        return daoInstrumentos.findByIdVendedor(idVendedor);
    }
}
