package com.web.myapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.myapp.model.Pedidos;
import com.web.myapp.repository.PedidosRepository;
import com.web.myapp.service.PedidosService;

@Service
public class PedidosServiceImpl implements PedidosService {

    @Autowired
    private PedidosRepository pedidosRepository;

    @Override
    public List<Pedidos> getAllPedidos() {
        return pedidosRepository.findAll();
    }

    @Override
    public Pedidos getPedidosById(Long id) {
        Optional<Pedidos> optionalPedido = pedidosRepository.findById(id);
        return optionalPedido.orElse(null);
    }

    @Override
    public Pedidos addPedidos(Pedidos pedidos) {
        return pedidosRepository.save(pedidos);
    }

    @Override
    public Pedidos editPedidos(Long id, Pedidos pedidosDetails) {
        Optional<Pedidos> optionalPedido = pedidosRepository.findById(id);
        if (optionalPedido.isPresent()) {
            Pedidos pedidos = optionalPedido.get();
            pedidos.setEstadoPago(pedidosDetails.getEstadoPago());
            pedidos.setFecha(pedidosDetails.getFecha());
            pedidos.setMonto(pedidosDetails.getMonto());
            pedidos.setComprador(pedidosDetails.getComprador());
            pedidos.setInstrumento(pedidosDetails.getInstrumento());
            pedidos.setTelefonoVendedor(pedidosDetails.getTelefonoVendedor());
            return pedidosRepository.save(pedidos);
        }
        return null;
    }

    @Override
    public void deletePedidos(Long id) {
        Optional<Pedidos> optionalPedido = pedidosRepository.findById(id);
        optionalPedido.ifPresent(pedidosRepository::delete);
    }
}
