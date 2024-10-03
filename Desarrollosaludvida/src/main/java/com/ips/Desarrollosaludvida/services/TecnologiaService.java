package com.ips.Desarrollosaludvida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ips.Desarrollosaludvida.models.TecnologiaModel;
import com.ips.Desarrollosaludvida.repositories.TecnologiaRepository;

@Service
public class TecnologiaService {

    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    public List<TecnologiaModel> obtenerTodos() {
        return tecnologiaRepository.findAll();
    }

    public TecnologiaModel obtenerPorId(Integer id) {
        return tecnologiaRepository.findById(id).orElse(null);
    }

    public TecnologiaModel crearTecnologia(TecnologiaModel tecnologia) {
        return tecnologiaRepository.save(tecnologia);
    }

    public TecnologiaModel actualizarTecnologia(Integer id, TecnologiaModel nuevaTecnologia) {
        TecnologiaModel tecnologiaActualizado = obtenerPorId(id);
        if (tecnologiaActualizado != null) {
            tecnologiaActualizado.setNombre(nuevaTecnologia.getNombre());
            tecnologiaActualizado.setDescripcion(nuevaTecnologia.getDescripcion());
            return tecnologiaRepository.save(tecnologiaActualizado);
        } else {
            return null;
        }
    }

    public void eliminarTecnologia(Integer id) {
        try {
            tecnologiaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la tecnologia con id " + id, e);
        }
    }

    public List<TecnologiaModel> buscarPorCategoria(String categoria) {
        return tecnologiaRepository.findByCategoria(categoria);
    }
}