package com.web.myapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.myapp.mapper.impl.UsuariosDto;
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
        Optional<Instrumentos> instrumento = daoInstrumentos.findById(instrumentos.getId());
        if (!instrumento.isPresent()) {
            System.out.println("El instrumento no existe");
            return null;
        }
        System.out.println("Instrumento guardado correctamente");
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
        return daoInstrumentos.findByIdVendedorAndActivo(idVendedor, true);
    }

    @Override
    public List<Instrumentos> getInstrumentosNotBelongToVendedor(Long userId) {
        return daoInstrumentos.findInstrumentosNotBelongToVendedor(userId);
    }
    
    public List<Instrumentos> getInstrumentosActivos() {
        return daoInstrumentos.findByActivoTrue();
    }

    public void actualizarEstadoActivo(Long id, boolean estado) {
        Optional<Instrumentos> optionalInstrumento = daoInstrumentos.findById(id);
        if (optionalInstrumento.isPresent()) {
            Instrumentos instrumento = optionalInstrumento.get();
            instrumento.setActivo(estado);
            daoInstrumentos.save(instrumento);
        } else {
            System.out.println("Instrumento no encontrado con id: " + id);
        }
    }

    @Override
    public List<Instrumentos> getInstrumentosByTipoInstrumentoAndVendedorId(Long tipoInstrumento, Long vendedorId) {
        return daoInstrumentos.findInstrumentosByTipoInstrumentoAndVendedorId(tipoInstrumento, vendedorId);
    }
}
