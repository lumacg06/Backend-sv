package com.ips.Desarrollosaludvida.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ips.Desarrollosaludvida.models.PacientesModel; // Asegúrate de que la ruta sea correcta
import com.ips.Desarrollosaludvida.repositories.PacientesRepository; // Asegúrate de que la ruta sea correcta

@Service
public class PacientesService {

    @Autowired
    private PacientesRepository pacienteRepository;

    // Obtener todos los pacientes
    public List<PacientesModel> obtenerTodos() {
        return pacienteRepository.findAll();
    }

    // Obtener paciente por ID
    public PacientesModel obtenerPorId(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    // Crear un nuevo paciente
    public PacientesModel crearPaciente(PacientesModel paciente) {
        validarVoluntadAnticipada(paciente);
        validarOposicionPresuncionLegal(paciente);
        return pacienteRepository.save(paciente);
    }

    // Actualizar un paciente existente
    public PacientesModel actualizarPaciente(Long id, PacientesModel nuevoPaciente) {
        PacientesModel pacienteActualizado = obtenerPorId(id);
        if (pacienteActualizado != null) {
            // Actualizar campos comunes
            pacienteActualizado.setPaisNacionalidad(nuevoPaciente.getPaisNacionalidad());
            pacienteActualizado.setTipoDocumento(nuevoPaciente.getTipoDocumento());
            pacienteActualizado.setDocumentoIdentificacion(nuevoPaciente.getDocumentoIdentificacion());
            pacienteActualizado.setPrimerNombre(nuevoPaciente.getPrimerNombre());
            pacienteActualizado.setSegundoNombre(nuevoPaciente.getSegundoNombre());
            pacienteActualizado.setPrimerApellido(nuevoPaciente.getPrimerApellido());
            pacienteActualizado.setSegundoApellido(nuevoPaciente.getSegundoApellido());
            pacienteActualizado.setFechaNacimiento(nuevoPaciente.getFechaNacimiento());
            pacienteActualizado.setSexoBiologico(nuevoPaciente.getSexoBiologico());
            pacienteActualizado.setIdentidadGenero(nuevoPaciente.getIdentidadGenero());
            pacienteActualizado.setOcupacionMomento(nuevoPaciente.getOcupacionMomento());
            pacienteActualizado.setCategoriaDiscapacidad(nuevoPaciente.getCategoriaDiscapacidad());
            pacienteActualizado.setMunicipioRecidenciaHabitual(nuevoPaciente.getMunicipioRecidenciaHabitual());
            pacienteActualizado.setPertenenciaEtnica(nuevoPaciente.getPertenenciaEtnica());
            pacienteActualizado.setZonaTerritorialResidencia(nuevoPaciente.getZonaTerritorialResidencia());
            pacienteActualizado.setEntidadRespondePlanBeneficios(nuevoPaciente.getEntidadRespondePlanBeneficios());

            // Validar voluntad anticipada
            if (!nuevoPaciente.getDocumentoVoluntadAnticipada()
                    .equals(pacienteActualizado.getDocumentoVoluntadAnticipada())) {
                validarVoluntadAnticipada(nuevoPaciente);
            }
            pacienteActualizado.setDocumentoVoluntadAnticipada(nuevoPaciente.getDocumentoVoluntadAnticipada());
            if ("SI".equalsIgnoreCase(nuevoPaciente.getDocumentoVoluntadAnticipada())) {
                pacienteActualizado.setFechaSuscripcionDocumento(nuevoPaciente.getFechaSuscripcionDocumento());
                pacienteActualizado.setCodigoPrestadorDocumento(nuevoPaciente.getCodigoPrestadorDocumento());
            } else {
                pacienteActualizado.setFechaSuscripcionDocumento(null);
                pacienteActualizado.setCodigoPrestadorDocumento(null);
            }

            // Validar oposición a la presunción legal de donación
            if (!nuevoPaciente.getOpocisionPresuncionLegalDonacion()
                    .equals(pacienteActualizado.getOpocisionPresuncionLegalDonacion())) {
                validarOposicionPresuncionLegal(nuevoPaciente);
            }
            pacienteActualizado
                    .setOpocisionPresuncionLegalDonacion(nuevoPaciente.getOpocisionPresuncionLegalDonacion());
            if ("NO".equalsIgnoreCase(nuevoPaciente.getOpocisionPresuncionLegalDonacion())) {
                pacienteActualizado.setFechaSuscripcionOposicion(nuevoPaciente.getFechaSuscripcionOposicion());
            } else {
                pacienteActualizado.setFechaSuscripcionOposicion(null);
            }

            return pacienteRepository.save(pacienteActualizado);
        } else {
            return null;
        }
    }

    // Validar voluntad anticipada
    private void validarVoluntadAnticipada(PacientesModel paciente) {
        if ("SI".equalsIgnoreCase(paciente.getDocumentoVoluntadAnticipada())) {
            if (paciente.getFechaSuscripcionDocumento() == null ||
                    paciente.getCodigoPrestadorDocumento() == null ||
                    paciente.getCodigoPrestadorDocumento().isEmpty()) {
                throw new IllegalArgumentException(
                        "Los campos de fecha de suscripción y código del prestador son obligatorios si la voluntad anticipada es 'Sí'.");
            }
        } else {
            // Si es "NO", asegurarse de que los campos adicionales estén vacíos
            paciente.setFechaSuscripcionDocumento(null);
            paciente.setCodigoPrestadorDocumento(null);
        }
    }

    // Validar oposición a la presunción legal de donación
    private void validarOposicionPresuncionLegal(PacientesModel paciente) {
        if ("NO".equalsIgnoreCase(paciente.getOpocisionPresuncionLegalDonacion())) {
            if (paciente.getFechaSuscripcionOposicion() == null) {
                throw new IllegalArgumentException(
                        "La fecha de suscripción del documento de oposición es obligatoria si la oposición es 'No'.");
            }
        } else {
            // Si es "Sí", asegurarse de que el campo de fecha de suscripción esté vacío
            paciente.setFechaSuscripcionOposicion(null);
        }
    }

    // Eliminar un paciente
    public void eliminarPaciente(Long id) {
        try {
            pacienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el paciente con id " + id, e);
        }
    }

    // Buscar pacientes por primer nombre
    public List<PacientesModel> buscarPorPrimerNombre(String primerNombre) {
        return pacienteRepository.findByPrimerNombreContainingIgnoreCase(primerNombre);
    }

    // Buscar pacientes por segundo nombre
    public List<PacientesModel> buscarPorSegundoNombre(String segundoNombre) {
        return pacienteRepository.findBySegundoNombreContainingIgnoreCase(segundoNombre);
    }

    // Buscar pacientes por primer apellido
    public List<PacientesModel> buscarPorPrimerApellido(String primerApellido) {
        return pacienteRepository.findByPrimerApellidoContainingIgnoreCase(primerApellido);
    }

    // Buscar pacientes por segundo apellido
    public List<PacientesModel> buscarPorSegundoApellido(String segundoApellido) {
        return pacienteRepository.findBySegundoApellidoContainingIgnoreCase(segundoApellido);
    }
}