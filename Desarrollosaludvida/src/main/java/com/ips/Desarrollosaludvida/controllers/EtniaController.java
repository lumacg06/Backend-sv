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

import com.ips.Desarrollosaludvida.models.EtniaModel;
import com.ips.Desarrollosaludvida.services.EtniaService;

@RestController
@RequestMapping("/api/etnias")
public class EtniaController {

    @Autowired
    private EtniaService etniaService;

    // Metodo GET
    @GetMapping
    public List<EtniaModel> buscarEtnias(@RequestParam (required = false)String etnia){
        if (etnia != null && !etnia.isEmpty()) {
            return etniaService.buscarPorEtnia(etnia);           
        } else {
          return etniaService.obtenerTodos();  
        }
    }
    //Metodo GET por ID
    @GetMapping("/{id}")    
    public ResponseEntity<EtniaModel> obtenerEtniaPorId(@PathVariable Long id){
       EtniaModel etnia = etniaService.obtenerPorId(id);
        if (etnia != null) {
            return ResponseEntity.ok(etnia);
       
        } else {
           return ResponseEntity.notFound().build(); 
        }
    }
    
    //Metodo POST
    @PostMapping
    public ResponseEntity<EtniaModel> crearEtnia(@RequestBody EtniaModel etnia){
        EtniaModel nuevoEtnia = etniaService.crearEtnia(etnia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEtnia);
    }
    
    //Metodo PUT
    @PutMapping("/{id}")
    public ResponseEntity<EtniaModel> actualizarEtnia(@PathVariable Long id, @RequestBody EtniaModel nuevaEtnia){
        EtniaModel etniaActualizado = etniaService.actualizarEtnia(id, nuevaEtnia);
        if (etniaActualizado != null) {
            return ResponseEntity.ok(etniaActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    //Metodo DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEtnia(@PathVariable Long id){
        etniaService.eliminarEtnia(id);
        return ResponseEntity.noContent().build();
    }
}