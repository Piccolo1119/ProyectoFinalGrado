package com.web.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.myapp.model.Pedidos;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
    
}
