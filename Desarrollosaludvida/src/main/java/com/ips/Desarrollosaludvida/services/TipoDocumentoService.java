package com.ips.Desarrollosaludvida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ips.Desarrollosaludvida.models.TipoDocumentoModel;
import com.ips.Desarrollosaludvida.repositories.TipoDocumentoRepository;

@Service
public class TipoDocumentoService {

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    public List<TipoDocumentoModel> obtenerTodos() {
        return tipoDocumentoRepository.findAll();
    }

    public TipoDocumentoModel obtenerPorId(Long id) {
        return tipoDocumentoRepository.findById(id).orElse(null);
    }

    public TipoDocumentoModel crearTipoDocumento(TipoDocumentoModel tipoDocumento) {
        return tipoDocumentoRepository.save(tipoDocumento);
    }

    public TipoDocumentoModel actualizarTipoDocumento(Long id, TipoDocumentoModel nuevoTipoDocumento) {
        TipoDocumentoModel tipoDocumentoActualizado = obtenerPorId(id);
        if (tipoDocumentoActualizado != null) {
            tipoDocumentoActualizado.setNombre(nuevoTipoDocumento.getNombre());
            tipoDocumentoActualizado.setCodigo(nuevoTipoDocumento.getCodigo());
            return tipoDocumentoRepository.save(tipoDocumentoActualizado);
        } else {
            return null;
        }
    }

    public void eliminarTipoDocumento(Long id) {
        try {
            tipoDocumentoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el tipo de documento con id " + id, e);
        }
    }

    public List<TipoDocumentoModel> buscarPorNombre(String nombre) {
        return tipoDocumentoRepository.findByNombre(nombre);
    }
}