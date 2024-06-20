package com.web.myapp.web.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.web.myapp.model.TipoInstrumentos;
import com.web.myapp.service.TipoInstrumentosService;

@CrossOrigin(origins = "*")
@Controller
public class TipoInstrumentosController {

    private final TipoInstrumentosService tipoInstrumentosService;

    public TipoInstrumentosController(TipoInstrumentosService tipoInstrumentosService) {
        this.tipoInstrumentosService = tipoInstrumentosService;
        
    }

    @GetMapping(value = "/tipoInstrumentos")
    public ResponseEntity<List<TipoInstrumentos>> getTipoInstrumentos() {
        try {
            return ResponseEntity.ok(tipoInstrumentosService.findAll());
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value = "/deleteTipoInstrumentos/{id}")
    public ResponseEntity<Boolean> deleteTipoInstrumento(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tipoInstrumentosService.deleteTipoInstrumentos(id));
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/addTipoInstrumentos")
    public ResponseEntity<TipoInstrumentos> addTipoInstrumento(@RequestBody TipoInstrumentos tipoInstrumentos) {
        try {
            return ResponseEntity.ok(tipoInstrumentosService.addTipoInstrumentos(tipoInstrumentos));
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/editTipoInstrumentos")
    public ResponseEntity<TipoInstrumentos> editTipoInstrumento(@RequestBody TipoInstrumentos tipoInstrumentos) {
        try {
            return ResponseEntity.ok(tipoInstrumentosService.editTipoInstrumentos(tipoInstrumentos));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
