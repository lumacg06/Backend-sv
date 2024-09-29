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

    // Metodo para obtener todas las etnias
    public List<EtniaModel> obtenerTodos() {
        return etniaRepository.findAll();
    }

    // Metodo para buscar etnias por etnia
    public List<EtniaModel> buscarPorEtnia(String etnia) {
        return etniaRepository.findByEtnia(etnia);
    }

    // Metodo para obtener una etnia por id
    public EtniaModel obtenerPorId(Long id) {
        return etniaRepository.findById(id).orElse(null);
    }

    // Metodo para crear una nueva etnia
    public EtniaModel crearEtnia(EtniaModel etnia) {
        return etniaRepository.save(etnia);
    }

    // Metodo para actualizar una etnia
    public EtniaModel actualizarEtnia(Long id, EtniaModel nuevaEtnia) {
        EtniaModel etniaActualizado = obtenerPorId(id);
        if (etniaActualizado != null) {
            etniaActualizado.setEtnia(nuevaEtnia.getEtnia());
            return etniaRepository.save(etniaActualizado);
        } else {
            return null;
        }
    }

    // Metodo para eliminar una etnia
    public void eliminarEtnia(Long id) {
        etniaRepository.deleteById(id);
    }
}