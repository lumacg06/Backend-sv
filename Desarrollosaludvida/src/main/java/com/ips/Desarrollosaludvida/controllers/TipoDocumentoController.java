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

import com.ips.Desarrollosaludvida.models.TipoDocumentoModel;
import com.ips.Desarrollosaludvida.services.TipoDocumentoService;

@RestController
@RequestMapping("/api/tipos-documento")
public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    // Metodo GET
    @GetMapping
    public List<TipoDocumentoModel> buscarTiposDocumento(@RequestParam(required = false) String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            return tipoDocumentoService.buscarPorNombre(nombre);
        } else {
            return tipoDocumentoService.obtenerTodos();
        }
    }

    // Metodo GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoDocumentoModel> obtenerTipoDocumentoPorId(@PathVariable Long id) {
        TipoDocumentoModel tipoDocumento = tipoDocumentoService.obtenerPorId(id);
        if (tipoDocumento != null) {
            return ResponseEntity.ok(tipoDocumento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo POST
    @PostMapping
    public ResponseEntity<TipoDocumentoModel> crearTipoDocumento(@RequestBody TipoDocumentoModel tipoDocumento) {
        tipoDocumento.setId(null); // Establece el id en null para que se genere automáticamente
        TipoDocumentoModel nuevoTipoDocumento = tipoDocumentoService.crearTipoDocumento(tipoDocumento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipoDocumento);
    }

    // Metodo PUT
    @PutMapping("/{id}")
    public ResponseEntity<TipoDocumentoModel> actualizarTipoDocumento(@PathVariable Long id, @RequestBody TipoDocumentoModel nuevoTipoDocumento) {
        TipoDocumentoModel tipoDocumentoActualizado = tipoDocumentoService.actualizarTipoDocumento(id, nuevoTipoDocumento);
        if (tipoDocumentoActualizado != null) {
            return ResponseEntity.ok(tipoDocumentoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoDocumento(@PathVariable Long id) {
        try {
            tipoDocumentoService.eliminarTipoDocumento(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}