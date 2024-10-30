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

import com.ips.Desarrollosaludvida.models.OcupacionModel;
import com.ips.Desarrollosaludvida.services.OcupacionService;

@RestController
@RequestMapping("/api/ocupaciones")
public class OcupacionController {

    @Autowired
    private OcupacionService ocupacionService;

    // Método GET para buscar todas las ocupaciones o por Descripcion
    @GetMapping
    public List<OcupacionModel> buscarOcupaciones(@RequestParam(required = false) String descripcion) {
        if (descripcion != null && !descripcion.isEmpty()) {
            return ocupacionService.buscarPorDescripcion(descripcion);
        } else {
            return ocupacionService.obtenerTodos();
        }
    }

    // Método GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<OcupacionModel> obtenerOcupacionPorId(@PathVariable Long id) {
        OcupacionModel ocupacion = ocupacionService.obtenerPorId(id);
        if (ocupacion != null) {
            return ResponseEntity.ok(ocupacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public List<OcupacionModel> buscarOcupacionPorDescripcion(@RequestParam String descripcion) {
        return ocupacionService.buscarPorDescripcion(descripcion);
    }

    // Método POST para crear una nueva ocupación
    @PostMapping
    public ResponseEntity<OcupacionModel> crearOcupacion(@RequestBody OcupacionModel ocupacion) {
        ocupacion.setId(null); // Establece el id en null para que se genere automáticamente
        OcupacionModel nuevaOcupacion = ocupacionService.crearOcupacion(ocupacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOcupacion);
    }

    // Método PUT para actualizar una ocupación existente
    @PutMapping("/{id}")
    public ResponseEntity<OcupacionModel> actualizarOcupacion(@PathVariable Long id,
            @RequestBody OcupacionModel nuevaOcupacion) {
        OcupacionModel ocupacionActualizada = ocupacionService.actualizarOcupacion(id, nuevaOcupacion);
        if (ocupacionActualizada != null) {
            return ResponseEntity.ok(ocupacionActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método DELETE para eliminar una ocupación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOcupacion(@PathVariable Long id) {
        try {
            ocupacionService.eliminarOcupacion(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}