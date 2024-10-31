package com.ips.Desarrollosaludvida.controllers;

import com.ips.Desarrollosaludvida.models.PacientesModel;
import com.ips.Desarrollosaludvida.services.PacientesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacientesController {

    @Autowired
    private PacientesService pacientesService;

    // Obtener todos los pacientes
    @GetMapping
    public ResponseEntity<List<PacientesModel>> obtenerTodos() {
        List<PacientesModel> pacientes = pacientesService.obtenerTodos();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    // Obtener paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<PacientesModel> obtenerPorId(@PathVariable Long id) {
        PacientesModel paciente = pacientesService.obtenerPorId(id);
        if (paciente != null) {
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo paciente
    @PostMapping
    public ResponseEntity<PacientesModel> crearPaciente(@RequestBody PacientesModel paciente) {
        PacientesModel nuevoPaciente = pacientesService.crearPaciente(paciente);
        return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
    }

    // Actualizar un paciente existente
    @PutMapping("/{id}")
    public ResponseEntity<PacientesModel> actualizarPaciente(@PathVariable Long id,
            @RequestBody PacientesModel nuevoPaciente) {
        PacientesModel pacienteActualizado = pacientesService.actualizarPaciente(id, nuevoPaciente);
        if (pacienteActualizado != null) {
            return new ResponseEntity<>(pacienteActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        try {
            pacientesService.eliminarPaciente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar pacientes por primer nombre
    @GetMapping("/buscar/primer-nombre/{primerNombre}")
    public ResponseEntity<List<PacientesModel>> buscarPorPrimerNombre(@PathVariable String primerNombre) {
        List<PacientesModel> pacientes = pacientesService.buscarPorPrimerNombre(primerNombre);
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    // Buscar pacientes por segundo nombre
    @GetMapping("/buscar/segundo-nombre/{segundoNombre}")
    public ResponseEntity<List<PacientesModel>> buscarPorSegundoNombre(@PathVariable String segundoNombre) {
        List<PacientesModel> pacientes = pacientesService.buscarPorSegundoNombre(segundoNombre);
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    // Buscar pacientes por primer apellido
    @GetMapping("/buscar/primer-apellido/{primerApellido}")
    public ResponseEntity<List<PacientesModel>> buscarPorPrimerApellido(@PathVariable String primerApellido) {
        List<PacientesModel> pacientes = pacientesService.buscarPorPrimerApellido(primerApellido);
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    // Buscar pacientes por segundo apellido
    @GetMapping("/buscar/segundo-apellido/{segundoApellido}")
    public ResponseEntity<List<PacientesModel>> buscarPorSegundoApellido(@PathVariable String segundoApellido) {
        List<PacientesModel> pacientes = pacientesService.buscarPorSegundoApellido(segundoApellido);
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }
}