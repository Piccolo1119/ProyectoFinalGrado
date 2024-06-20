package com.web.myapp.service;

import java.util.List;
import java.util.Optional;

import com.web.myapp.mapper.impl.PedidosDto;
import com.web.myapp.model.Instrumentos;
import com.web.myapp.model.Pedidos;

public interface PedidosService {
    List<Pedidos> findAll();
    Pedidos addPedidos(Pedidos pedidos);
    List<Pedidos> getPedidosByComprador(Long userId);
}