package com.ips.Desarrollosaludvida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ips.Desarrollosaludvida.models.OcupacionModel;
import com.ips.Desarrollosaludvida.repositories.OcupacionRepository;

@Service
public class OcupacionService {

    @Autowired
    private OcupacionRepository ocupacionRepository;

    public List<OcupacionModel> obtenerTodos() {
        return ocupacionRepository.findAll();
    }

    public OcupacionModel obtenerPorId(Long id) {
        return ocupacionRepository.findById(id).orElse(null);
    }

    public OcupacionModel crearOcupacion(OcupacionModel ocupacion) {
        return ocupacionRepository.save(ocupacion);
    }

    public OcupacionModel actualizarOcupacion(Long id, OcupacionModel nuevaOcupacion) {
        OcupacionModel ocupacionActualizado = obtenerPorId(id);
        if (ocupacionActualizado != null) {
            ocupacionActualizado.setDescripcion(nuevaOcupacion.getDescripcion());
            // Agrega aquí los demás campos que deseas actualizar
            return ocupacionRepository.save(ocupacionActualizado);
        } else {
            return null;
        }
    }

    public void eliminarOcupacion(Long id) {
        try {
            ocupacionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la ocupación con id " + id, e);
        }
    }

    public List<OcupacionModel> buscarPorDescripcion(String descripcion) {
        return ocupacionRepository.findByDescripcionContainingIgnoreCase(descripcion);
    }
}