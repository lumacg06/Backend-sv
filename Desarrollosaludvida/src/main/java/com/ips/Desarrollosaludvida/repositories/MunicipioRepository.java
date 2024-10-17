package com.ips.Desarrollosaludvida.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ips.Desarrollosaludvida.models.MunicipioModel;

@Repository
public interface MunicipioRepository extends JpaRepository<MunicipioModel, Long> {

    @Query("SELECT m FROM MunicipioModel m WHERE LOWER(m.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<MunicipioModel> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);

}