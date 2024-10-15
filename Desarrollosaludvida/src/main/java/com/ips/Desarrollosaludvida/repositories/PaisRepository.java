package com.ips.Desarrollosaludvida.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ips.Desarrollosaludvida.models.PaisModel;

@Repository
public interface PaisRepository extends JpaRepository<PaisModel, Long> {

    List<PaisModel> findByNombre(String nombre);
}