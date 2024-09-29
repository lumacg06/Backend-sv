package com.ips.Desarrollosaludvida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ips.Desarrollosaludvida.models.DiscapacidadModel;
import com.ips.Desarrollosaludvida.repositories.DiscapacidadRepository;

@Service
public class DiscapacidadService {

    @Autowired
    private DiscapacidadRepository discapacidadRepository;

    // Metodo para obtener todos los discapacidades
    public List<DiscapacidadModel> obtenerTodos() {
        return discapacidadRepository.findAll();
    }

    // Metodo para buscar discapacidades por categoria
    public List<DiscapacidadModel> buscarPorCategoria(String categoria) {
        return discapacidadRepository.findByCategoria(categoria);
    }

    // Metodo para obtener un discapacidad por id
    public DiscapacidadModel obtenerPorId(Long id) {
        return discapacidadRepository.findById(id).orElse(null);
    }

    // Metodo para crear un nuevo discapacidad
    public DiscapacidadModel crearDiscapacidad(DiscapacidadModel discapacidad) {
        return discapacidadRepository.save(discapacidad);
    }

    // Metodo para actualizar un discapacidad
    public DiscapacidadModel actualizarDiscapacidad(Long id, DiscapacidadModel nuevoDiscapacidad) {
        DiscapacidadModel discapacidadActualizado = obtenerPorId(id);
        if (discapacidadActualizado != null) {
            discapacidadActualizado.setCategoria(nuevoDiscapacidad.getCategoria());
            return discapacidadRepository.save(discapacidadActualizado);
        } else {
            return null;
        }
    }

    // Metodo para eliminar un discapacidad
    public void eliminarDiscapacidad(Long id) {
        discapacidadRepository.deleteById(id);
    }
}