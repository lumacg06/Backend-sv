package com.ips.Desarrollosaludvida.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ips.Desarrollosaludvida.models.PaisModel;

@Repository
public interface PaisRepository extends JpaRepository<PaisModel, Long> {

    @Query("SELECT p FROM PaisModel p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<PaisModel> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);
}