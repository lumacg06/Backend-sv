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

import com.ips.Desarrollosaludvida.models.TecnologiaModel;
import com.ips.Desarrollosaludvida.services.TecnologiaService;

@RestController
@RequestMapping("/api/tecnologias")
public class TecnologiaController {

    @Autowired
    private TecnologiaService tecnologiaService;

    // Metodo GET
    @GetMapping
    public List<TecnologiaModel> buscarTecnologias(@RequestParam(required = false) String categoria) {
        if (categoria != null && !categoria.isEmpty()) {
            return tecnologiaService.buscarPorCategoria(categoria);
        } else {
            return tecnologiaService.obtenerTodos();
        }
    }

    // Metodo GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<TecnologiaModel> obtenerTecnologiaPorId(@PathVariable Long id) {
        TecnologiaModel tecnologia = tecnologiaService.obtenerPorId(id);
        if (tecnologia != null) {
            return ResponseEntity.ok(tecnologia);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo POST
    @PostMapping
    public ResponseEntity<TecnologiaModel> crearTecnologia(@RequestBody TecnologiaModel tecnologia) {
        TecnologiaModel nuevoTecnologia = tecnologiaService.crearTecnologia(tecnologia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTecnologia);
    }

    // Metodo PUT
    @PutMapping("/{id}")
    public ResponseEntity<TecnologiaModel> actualizarTecnologia(@PathVariable Long id,
            @RequestBody TecnologiaModel nuevoTecnologia) {
        TecnologiaModel TecnologiaActualizado = tecnologiaService.actualizarTecnologia(id, nuevoTecnologia);
        if (TecnologiaActualizado != null) {
            return ResponseEntity.ok(TecnologiaActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTecnologia(@PathVariable Long id) {
        try {
            tecnologiaService.eliminarTecnologia(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}