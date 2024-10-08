package com.ips.Desarrollosaludvida.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ips.Desarrollosaludvida.models.DiscapacidadModel;
import com.ips.Desarrollosaludvida.services.DiscapacidadService;

@RestController
@RequestMapping("/api/discapacidades")
public class DiscapacidadController {

    @Autowired
    private DiscapacidadService discapacidadService;

    // Metodo GET
    @GetMapping
    public List<DiscapacidadModel> buscaDiscapacidades(@RequestParam(required = false) String categoria) {
        if (categoria != null && !categoria.isEmpty()) {
            return discapacidadService.buscarPorCategoria(categoria);
        } else {
            return discapacidadService.obtenerTodos();
        }
    }

    // Metodo GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<DiscapacidadModel> obtenerDiscapacidadPorId(@PathVariable Long id) {
        DiscapacidadModel discapacidad = discapacidadService.obtenerPorId(id);
        if (discapacidad != null) {
            return ResponseEntity.ok(discapacidad);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo POST
    @PostMapping
    public ResponseEntity<DiscapacidadModel> crearDiscapacidad(@RequestBody DiscapacidadModel discapacidad) {
        discapacidad.setId(null); // Establece el id en null para que se genere automáticamente
        DiscapacidadModel nuevoDiscapacidad = discapacidadService.crearDiscapacidad(discapacidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDiscapacidad);
    }

    // Metodo PUT
    @PutMapping("/{id}")
    public ResponseEntity<DiscapacidadModel> actualizarDiscapacidad(@PathVariable Long id,
            @RequestBody DiscapacidadModel nuevoDiscapacidad) {
        DiscapacidadModel discapacidadActualizado = discapacidadService.actualizarDiscapacidad(id, nuevoDiscapacidad);
        if (discapacidadActualizado != null) {
            return ResponseEntity.ok(discapacidadActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDiscapacidad(@PathVariable Long id) {
        try {
            discapacidadService.eliminarDiscapacidad(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}