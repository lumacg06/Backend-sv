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

import com.ips.Desarrollosaludvida.models.MunicipioModel;
import com.ips.Desarrollosaludvida.services.MunicipioService;

@RestController
@RequestMapping("/api/municipios")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    // Metodo GET
    @GetMapping
    public List<MunicipioModel> buscarMunicipios(@RequestParam(required = false) String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            return municipioService.buscarPorNombre(nombre);
        } else {
            return municipioService.obtenerTodos();
        }
    }

    // Metodo GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<MunicipioModel> obtenerMunicipioPorId(@PathVariable Long id) {
        MunicipioModel municipio = municipioService.obtenerPorId(id);
        if (municipio != null) {
            return ResponseEntity.ok(municipio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo GET por nombre
    @GetMapping("/buscar")
    public List<MunicipioModel> buscarMunicipiosPorNombre(@RequestParam String nombre) {
        return municipioService.buscarPorNombre(nombre);
    }

    // Metodo POST
    @PostMapping
    public ResponseEntity<MunicipioModel> crearMunicipio(@RequestBody MunicipioModel municipio) {
        municipio.setId(null); // Establece el id en null para que se genere automáticamente
        MunicipioModel nuevoMunicipio = municipioService.crearMunicipio(municipio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMunicipio);
    }

    // Metodo PUT
    @PutMapping("/{id}")
    public ResponseEntity<MunicipioModel> actualizarMunicipio(@PathVariable Long id,
            @RequestBody MunicipioModel nuevoMunicipio) {
        MunicipioModel municipioActualizado = municipioService.actualizarMunicipio(id, nuevoMunicipio);
        if (municipioActualizado != null) {
            return ResponseEntity.ok(municipioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMunicipio(@PathVariable Long id) {
        try {
            municipioService.eliminarMunicipio(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}