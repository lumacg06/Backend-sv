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
import jakarta.persistence.Column;

@Entity 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes")
public class PacientesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pais_nacionalidad") // Asegúrate de que el nombre coincida con el de la base de datos
    private String paisNacionalidad; 
    @Column(name = "tipo_documento") 
    private String tipoDocumento; 
    @Column(name = "documento_identificacion") 
    private String documentoIdentificacion; 
    @Column(name = "primer_nombre") 
    private String primerNombre; 
    @Column(name = "segundo_nombre") 
    private String segundoNombre; 
    @Column(name = "primer_apellido") 
    private String primerApellido; 
    @Column(name = "segundo_apellido") 
    private String segundoApellido; 
    @Column(name = "fecha_nacimiento") 
    private Date fechaNacimiento; 
    @Column(name = "sexo_biologico") 
    private String sexoBiologico;
    @Column(name = "identidad_genero") 
    private String identidadGenero;
    @Column(name = "ocupacion_momento") 
    private String ocupacionMomento;
    @Column(name = "oposicion_presuncion_legal_donacion") // Asegúrate de que el nombre coincida
    private String opocisionPresuncionLegalDonacion; // "Sí" o "No"
    @Column(name = "fecha_suscripcion_oposicion") 
    private Date fechaSuscripcionOposicion; 
    @Column(name = "documento_voluntad_anticipada") 
    private String documentoVoluntadAnticipada; 
    @Column(name = "fecha_suscripcion_documento") 
    private Date fechaSuscripcionDocumento; 
    @Column(name = "codigo_prestador_documento") 
    private String codigoPrestadorDocumento; 
    @Column(name = "categoria_discapacidad")   
    private String categoria_Discapacidad;   
    @Column(name = "pais_residencia_habitual") 
    private String paisResidenciaHabitual;
    @Column(name = "municipio_recidencia_habitual") 
    private String municipioRecidenciaHabitual;
    @Column(name = "pertenencia_etnica") 
    private String pertenenciaEtnica;
    @Column(name = "zona_territorial_residencia") 
    private String zonaTerritorialResidencia;
    @Column(name = "entidad_responde_plan_beneficios") 
    private String entidadRespondePlanBeneficios;
}