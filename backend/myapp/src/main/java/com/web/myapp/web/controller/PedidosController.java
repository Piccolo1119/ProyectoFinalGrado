package com.web.myapp.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.web.myapp.jwt.JwtService;
import com.web.myapp.mapper.impl.UsuariosDto;
import com.web.myapp.model.Instrumentos;
import com.web.myapp.model.Pedidos;
import com.web.myapp.service.InstrumentosService;
import com.web.myapp.service.PedidosService;
import com.web.myapp.service.UsuariosService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")

@Controller
@RequiredArgsConstructor
public class PedidosController {

    @Autowired
    private PedidosService pedidosService;

    private final InstrumentosService instrumentosService;
    private final UsuariosService usuariosService;
    private final JwtService jwtService;

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedidos>> getPedidos() {
        try {
            return ResponseEntity.ok(pedidosService.findAll());
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/pedidos/user")
    public ResponseEntity<List<Pedidos>> getPedidosByComprador(@RequestHeader("Authorization") String token) {
        try {
            String username = jwtService.getUsernameFromToken(token.substring(7));
            UsuariosDto usuariosDto = usuariosService.getUsuariosByUsername(username);
            List<Pedidos> pedidos = pedidosService.getPedidosByComprador(usuariosDto.getId());
            if (pedidos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addPedidos")
    public ResponseEntity<Pedidos> addPedidos(@RequestBody Pedidos pedidos, @RequestHeader("Authorization") String token) {
        String username = jwtService.getUsernameFromToken(token.substring(7));
        UsuariosDto usuariosDto = usuariosService.getUsuariosByUsername(username);
        pedidos.setComprador(usuariosDto.getId());
        try {
            Pedidos nuevoPedido = pedidosService.addPedidos(pedidos);
            if (nuevoPedido != null) {
                return ResponseEntity.ok(nuevoPedido);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
