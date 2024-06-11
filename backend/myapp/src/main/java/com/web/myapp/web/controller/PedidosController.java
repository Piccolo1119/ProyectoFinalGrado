package com.web.myapp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.myapp.model.Pedidos;
import com.web.myapp.service.PedidosService;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosService pedidosService;

    @GetMapping
    public ResponseEntity<List<Pedidos>> getAllPedidos() {
        List<Pedidos> pedidos = pedidosService.getAllPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> getPedidosById(@PathVariable("id") Long id) {
        Pedidos pedidos = pedidosService.getPedidosById(id);
        if (pedidos != null) {
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Pedidos> addPedidos(@RequestBody Pedidos pedidos) {
        Pedidos createdPedidos = pedidosService.addPedidos(pedidos);
        return new ResponseEntity<>(createdPedidos, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedidos> editPedidos(@PathVariable("id") Long id, @RequestBody Pedidos pedidosDetails) {
        Pedidos editedPedidos = pedidosService.editPedidos(id, pedidosDetails);
        if (editedPedidos != null) {
            return new ResponseEntity<>(editedPedidos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidos(@PathVariable("id") Long id) {
        pedidosService.deletePedidos(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
