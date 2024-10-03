package com.ips.Desarrollosaludvida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ips.Desarrollosaludvida.models.TecnologiaModel;
import java.util.List;

@Repository
public interface TecnologiaRepository extends JpaRepository<TecnologiaModel, Integer> {

    List<TecnologiaModel> findByCategoria(String categoria);
}