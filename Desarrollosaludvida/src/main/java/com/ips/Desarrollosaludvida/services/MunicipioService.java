package com.ips.Desarrollosaludvida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ips.Desarrollosaludvida.models.MunicipioModel;
import com.ips.Desarrollosaludvida.repositories.MunicipioRepository;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    public List<MunicipioModel> obtenerTodos() {
        return municipioRepository.findAll();
    }

    public MunicipioModel obtenerPorId(Long id) {
        return municipioRepository.findById(id).orElse(null);
    }

    public MunicipioModel crearMunicipio(MunicipioModel municipio) {
        return municipioRepository.save(municipio);
    }

    public MunicipioModel actualizarMunicipio(Long id, MunicipioModel nuevoMunicipio) {
        MunicipioModel municipioActualizado = obtenerPorId(id);
        if (municipioActualizado != null) {
            municipioActualizado.setNombre(nuevoMunicipio.getNombre());
            return municipioRepository.save(municipioActualizado);
        } else {
            return null;
        }
    }

    public void eliminarMunicipio(Long id) {
        try {
            municipioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el municipio con id " + id, e);
        }
    }

    public List<MunicipioModel> buscarPorNombre(String nombre) {
        // Utiliza el método findByNombre de Spring Data JPA
        return municipioRepository.findByNombreContainingIgnoreCase(nombre);
    }
}