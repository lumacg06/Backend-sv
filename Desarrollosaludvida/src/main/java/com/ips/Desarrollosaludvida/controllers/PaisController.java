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

import com.ips.Desarrollosaludvida.models.PaisModel;
import com.ips.Desarrollosaludvida.services.PaisService;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisService paisService;

    // Metodo GET
    @GetMapping
    public List<PaisModel> buscarPaises(@RequestParam(required = false) String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            return paisService.buscarPorNombre(nombre);
        } else {
            return paisService.obtenerTodos();
        }
    }

    // Metodo GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<PaisModel> obtenerPaisPorId(@PathVariable Long id) {
        PaisModel pais = paisService.obtenerPorId(id);
        if (pais != null) {
            return ResponseEntity.ok(pais);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo POST
    @PostMapping
    public ResponseEntity<PaisModel> crearPais(@RequestBody PaisModel pais) {
        pais.setId(null); // Establece el id en null para que se genere automáticamente
        PaisModel nuevoPais = paisService.crearPais(pais);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPais);
    }

    // Metodo PUT
    @PutMapping("/{id}")
    public ResponseEntity<PaisModel> actualizarPais(@PathVariable Long id,
            @RequestBody PaisModel nuevoPais) {
        PaisModel paisActualizado = paisService.actualizarPais(id, nuevoPais);
        if (paisActualizado != null) {
            return ResponseEntity.ok(paisActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPais(@PathVariable Long id) {
        try {
            paisService.eliminarPais(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}