package com.ips.Desarrollosaludvida.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ips.Desarrollosaludvida.models.OcupacionModel;

@Repository
public interface OcupacionRepository extends JpaRepository<OcupacionModel, Long> {

    // Usando el método de consulta derivada
    List<OcupacionModel> findByDescripcionContainingIgnoreCase(String descripcion); // Sin necesidad de @Query
}