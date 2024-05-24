package com.web.myapp.web.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.web.myapp.model.Instrumentos;
import com.web.myapp.service.InstrumentosService;

@CrossOrigin(origins = "*")
@Controller
public class InstrumentosController {

    private final InstrumentosService instrumentosService;

    public InstrumentosController(InstrumentosService instrumentosService) {
        this.instrumentosService = instrumentosService;
    }

    @GetMapping("/instrumentos")
    public ResponseEntity<List<Instrumentos>> getInstrumentos() {
        try {
            return ResponseEntity.ok(instrumentosService.findAll());
        
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/deleteInstrumentos/{id}")
    public ResponseEntity<Boolean> deleteInstrumentos(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(instrumentosService.deleteInstrumentos(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addInstrumentos")
    public ResponseEntity<Instrumentos> addInstrumentos(@RequestBody Instrumentos instrumentos) {
        try {
            return ResponseEntity.ok(instrumentosService.addInstrumentos(instrumentos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/editInstrumentos")
    public ResponseEntity<Instrumentos> editInstrumentos(@RequestBody Instrumentos instrumentos) {
        try {
            return ResponseEntity.ok(instrumentosService.editInstrumentos(instrumentos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
