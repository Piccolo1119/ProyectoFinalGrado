package com.web.myapp.service;

import java.util.List;

import com.web.myapp.model.Pedidos;

public interface PedidosService {
    List<Pedidos> getAllPedidos();
    Pedidos getPedidosById(Long id);
    Pedidos addPedidos(Pedidos pedidos);
    Pedidos editPedidos(Long id, Pedidos pedidos);
    void deletePedidos(Long id);
}