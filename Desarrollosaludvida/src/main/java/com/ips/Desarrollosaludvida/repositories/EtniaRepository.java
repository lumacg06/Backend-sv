package com.ips.Desarrollosaludvida.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ips.Desarrollosaludvida.models.EtniaModel;

@Repository
public interface EtniaRepository extends JpaRepository<EtniaModel, Long> {

    List<EtniaModel> findByEtnia(String etnia);
}