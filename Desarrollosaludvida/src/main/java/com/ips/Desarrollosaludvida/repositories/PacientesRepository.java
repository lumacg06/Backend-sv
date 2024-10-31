package com.ips.Desarrollosaludvida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ips.Desarrollosaludvida.models.PacientesModel;

import java.util.List;

@Repository
public interface PacientesRepository extends JpaRepository<PacientesModel, Long> {
    List<PacientesModel> findByPrimerNombreContainingIgnoreCase(String primerNombre);
    List<PacientesModel> findBySegundoNombreContainingIgnoreCase(String segundoNombre);
    List<PacientesModel> findByPrimerApellidoContainingIgnoreCase(String primerApellido);
    List<PacientesModel> findBySegundoApellidoContainingIgnoreCase(String segundoApellido);
}