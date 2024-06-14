package com.web.myapp.web.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.web.myapp.jwt.JwtService;
import com.web.myapp.mapper.impl.UsuariosDto;
import com.web.myapp.model.Instrumentos;
import com.web.myapp.service.InstrumentosService;
import com.web.myapp.service.UsuariosService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@Controller
@RequiredArgsConstructor
public class InstrumentosController {

    private final InstrumentosService instrumentosService;
    private final UsuariosService usuariosService;
    private final JwtService jwtService;

    @GetMapping("/instrumentos")
    public ResponseEntity<List<Instrumentos>> getInstrumentos() {
        try {
            return ResponseEntity.ok(instrumentosService.findAll());
        
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/instrumentos/activos")
    public ResponseEntity<List<Instrumentos>> getInstrumentosActivos() {
        List<Instrumentos> instrumentosActivos = instrumentosService.getInstrumentosActivos();
        return ResponseEntity.ok().body(instrumentosActivos);
    }

    @PostMapping("/activo/{id}")
    public ResponseEntity<Boolean> actualizarEstadoActivo(@PathVariable Long id) {
        try {
            instrumentosService.actualizarEstadoActivo(id, false); // Pasar false para desactivar
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteInstrumentos/{id}")
    public ResponseEntity<Boolean> deleteInstrumentos(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(instrumentosService.deleteInstrumentos(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addInstrumentos")
    public ResponseEntity<Instrumentos> addInstrumentos(@RequestBody Instrumentos instrumentos, @RequestHeader("Authorization") String token) {
        String username = jwtService.getUsernameFromToken(token.substring(7));
        UsuariosDto usuariosDto = usuariosService.getUsuariosByUsername(username);
        instrumentos.setIdVendedor(usuariosDto.getId());
        instrumentos.setActivo(true);
        try {
            return ResponseEntity.ok(instrumentosService.addInstrumentos(instrumentos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/editInstrumentos/{id}")
    public ResponseEntity<Instrumentos> editInstrumentos(@RequestBody Instrumentos instrumentos) {
        try {
            return ResponseEntity.ok(instrumentosService.editInstrumentos(instrumentos));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/findByTipoinstrumentos")
    public ResponseEntity<List<Instrumentos>> getInstrumentosByTipoInstrumento(@RequestParam("tipo_instrumento") Long tipoInstrumentoId) {
        try {
            List<Instrumentos> instrumentos = instrumentosService.findByTipoInstrumentoId(tipoInstrumentoId);
            return ResponseEntity.ok(instrumentos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/instrumentos/{id}")
    public ResponseEntity<Instrumentos> getInstrumentoById(@PathVariable Long id) {
        // Llama al servicio para obtener el instrumento por su ID
        return instrumentosService.findInstrumentosById(id)
                .map(instrumento -> ResponseEntity.ok().body(instrumento))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/instrumentos/user")
    public ResponseEntity<List<Instrumentos>> getInstrumentosByVendedorId(@RequestHeader("Authorization") String token) {
        try {
            String username = jwtService.getUsernameFromToken(token.substring(7));
            UsuariosDto usuariosDto = usuariosService.getUsuariosByUsername(username);
            List<Instrumentos> instrumentos = instrumentosService.getInstrumentosByVendedorId(usuariosDto.getId());
            if (instrumentos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(instrumentos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/instrumentos/usernot")
    public ResponseEntity<List<Instrumentos>> findByIdVendedorNot(@RequestHeader("Authorization") String token) {
        try {
            String username = jwtService.getUsernameFromToken(token.substring(7));
            UsuariosDto usuariosDto = usuariosService.getUsuariosByUsername(username);
            List<Instrumentos> instrumentos = instrumentosService.getInstrumentosNotBelongToVendedor(usuariosDto.getId());
            if (instrumentos.isEmpty() ) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(instrumentos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/instrumentos/instrumentosTipoUser")
    public ResponseEntity<List<Instrumentos>> getInstrumentosByTipoInstrumentoAndVendedorId(@RequestParam("tipo_instrumento") String tipoInstrumentoId, @RequestHeader("Authorization") String token) {
        try {
            String username = jwtService.getUsernameFromToken(token.substring(7));
            UsuariosDto usuariosDto = usuariosService.getUsuariosByUsername(username);
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            System.out.println(username);
            List<Instrumentos> instrumentos = instrumentosService.getInstrumentosByTipoInstrumentoAndVendedorId(Long.parseLong(tipoInstrumentoId), usuariosDto.getId());
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            System.out.println(instrumentos);
            if (instrumentos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(instrumentos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
