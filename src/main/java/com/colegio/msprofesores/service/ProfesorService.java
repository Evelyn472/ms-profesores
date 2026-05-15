package com.colegio.msprofesores.service;

import com.colegio.msprofesores.dto.ProfesorRequestDTO;
import com.colegio.msprofesores.dto.ProfesorResponseDTO;
import com.colegio.msprofesores.model.Profesor;
import com.colegio.msprofesores.repository.ProfesorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    // Listar todos los profesores
    public List<ProfesorResponseDTO> obtenerTodos() {
        return profesorRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    // Obtener un profesor por ID
    public ProfesorResponseDTO obtenerPorId(Long id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        return convertirADto(profesor);
    }

    // Guardar nuevo profesor
    public ProfesorResponseDTO guardar(ProfesorRequestDTO request) {
        Profesor profesor = new Profesor();
        profesor.setNombre(request.getNombre());
        profesor.setApellido(request.getApellido());
        profesor.setEmail(request.getEmail());
        profesor.setEspecialidad(request.getEspecialidad());

        Profesor guardado = profesorRepository.save(profesor);
        return convertirADto(guardado);
    }

    // Método auxiliar de conversión (Entidad -> DTO)
    private ProfesorResponseDTO convertirADto(Profesor profesor) {
        ProfesorResponseDTO dto = new ProfesorResponseDTO();
        dto.setId(profesor.getId());
        dto.setNombre(profesor.getNombre());
        dto.setApellido(profesor.getApellido());
        dto.setEmail(profesor.getEmail());
        dto.setEspecialidad(profesor.getEspecialidad());
        return dto;
    }
}