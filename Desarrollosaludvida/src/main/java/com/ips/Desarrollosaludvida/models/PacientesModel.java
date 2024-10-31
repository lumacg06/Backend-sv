package com.ips.Desarrollosaludvida.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name =  "pacientes")
public class PacientesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paisNacionalidad; 
    private String tipoDocumento; 
    private String documentoIdentificacion; 
    private String primerNombre; 
    private String segundoNombre; 
    private String primerApellido; 
    private String segundoApellido; 
    private Date fechaNacimiento; 
    private String sexoBiologico;
    private String identidadGenero;
    private String ocupacionMomento;
    private String opocisionPresuncionLegalDonacion; // "Sí" o "No"
    private Date fechaSuscripcionOposicion; // Fecha de suscripción del documento de oposición
    // Modificación del campo de voluntad anticipada
    private String documentoVoluntadAnticipada; // "Sí" o "No"
    private Date fechaSuscripcionDocumento; // Fecha de suscripción del documento
    private String codigoPrestadorDocumento; // Código del prestador donde se encuentra el documento

    private String categoriaDiscapacidad;
    private String paisResidenciaHabitual;
    private String municipioRecidenciaHabitual;
    private String pertenenciaEtnica;
    private String zonaTerritorialResidencia;
    private String entidadRespondePlanBeneficios;
}