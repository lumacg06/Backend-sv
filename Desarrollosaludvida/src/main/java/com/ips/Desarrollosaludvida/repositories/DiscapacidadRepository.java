package com.ips.Desarrollosaludvida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ips.Desarrollosaludvida.models.DiscapacidadModel;
import java.util.List;

@Repository
public interface DiscapacidadRepository extends JpaRepository<DiscapacidadModel, Long> {

    List<DiscapacidadModel> findByCategoria(String categoria);
}