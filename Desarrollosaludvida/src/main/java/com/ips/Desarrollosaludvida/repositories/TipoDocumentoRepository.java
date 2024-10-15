package com.ips.Desarrollosaludvida.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ips.Desarrollosaludvida.models.TipoDocumentoModel;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoModel, Long> {

    List<TipoDocumentoModel> findByNombre(String nombre);
}