package com.ips.Desarrollosaludvida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ips.Desarrollosaludvida.models.PaisModel;
import com.ips.Desarrollosaludvida.repositories.PaisRepository;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public List<PaisModel> obtenerTodos() {
        return paisRepository.findAll();
    }

    public PaisModel obtenerPorId(Long id) {
        return paisRepository.findById(id).orElse(null);
    }

    public PaisModel crearPais(PaisModel pais) {
        return paisRepository.save(pais);
    }

    public PaisModel actualizarPais(Long id, PaisModel nuevaPais) {
        PaisModel paisActualizado = obtenerPorId(id);
        if (paisActualizado != null) {
            paisActualizado.setNombre(nuevaPais.getNombre());
            paisActualizado.setCodigoIso(nuevaPais.getCodigoIso());
            return paisRepository.save(paisActualizado);
        } else {
            return null;
        }
    }

    public void eliminarPais(Long id) {
        try {
            paisRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el país con id " + id, e);
        }
    }

    public List<PaisModel> buscarPorNombre(String nombre) {
        return paisRepository.findByNombre(nombre);
    }

}