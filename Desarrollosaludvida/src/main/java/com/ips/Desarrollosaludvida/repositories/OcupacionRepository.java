package com.ips.Desarrollosaludvida.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ips.Desarrollosaludvida.models.OcupacionModel;

@Repository
public interface OcupacionRepository extends JpaRepository<OcupacionModel, Long> {

    @Query("SELECT o FROM OcupacionModel o WHERE LOWER(o.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<OcupacionModel> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);
}