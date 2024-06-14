package com.web.myapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.myapp.model.Pedidos;
import com.web.myapp.repository.PedidosRepository;
import com.web.myapp.service.InstrumentosService;
import com.web.myapp.service.PedidosService;

@Service
public class PedidosServiceImpl implements PedidosService {

    @Autowired
    private PedidosRepository pedidosRepository;
    @Autowired
    private InstrumentosService instrumentosService;

    @Override
    public List<Pedidos> findAll() {
        List<Pedidos> listPedidos = pedidosRepository.findAll();
        return listPedidos;
    }

    @Override
    public Pedidos addPedidos(Pedidos pedidos) {
        instrumentosService.actualizarEstadoActivo(pedidos.getInstrumento().getId(), false);
        return pedidosRepository.save(pedidos);
    }

    @Override
    public List<Pedidos> getPedidosByComprador(Long userId) {
        return pedidosRepository.findByComprador(userId);
    }
}
