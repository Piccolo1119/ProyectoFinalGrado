package com.web.myapp.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.myapp.model.Pedidos;
import java.util.List;


@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long> {

    List<Pedidos> findByComprador(Long comprador);

}
