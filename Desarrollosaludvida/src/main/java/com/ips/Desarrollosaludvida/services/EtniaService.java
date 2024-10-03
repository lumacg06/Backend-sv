package com.ips.Desarrollosaludvida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ips.Desarrollosaludvida.models.EtniaModel;
import com.ips.Desarrollosaludvida.repositories.EtniaRepository;

@Service
public class EtniaService {

    @Autowired
    private EtniaRepository etniaRepository;

    public List<EtniaModel> obtenerTodos() {
        return etniaRepository.findAll();
    }

    public EtniaModel obtenerPorId(Long id) {
        return etniaRepository.findById(id).orElse(null);
    }

    public EtniaModel crearEtnia(EtniaModel etnia) {
        return etniaRepository.save(etnia);
    }

    public EtniaModel actualizarEtnia(Long id, EtniaModel nuevaEtnia) {
        EtniaModel etniaActualizado = obtenerPorId(id);
        if (etniaActualizado != null) {
            etniaActualizado.setEtnia(nuevaEtnia.getEtnia());
            return etniaRepository.save(etniaActualizado);
        } else {
            return null;
        }
    }

    public void eliminarEtnia(Long id) {
        try {
            etniaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la etnia con id " + id, e);
        }
    }

    public List<EtniaModel> buscarPorEtnia(String etnia) {
        return etniaRepository.findByEtnia(etnia);
    }
}