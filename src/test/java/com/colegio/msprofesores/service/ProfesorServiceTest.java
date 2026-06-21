package com.colegio.msprofesores.service;

import com.colegio.msprofesores.dto.ProfesorRequestDTO;
import com.colegio.msprofesores.dto.ProfesorResponseDTO;
import com.colegio.msprofesores.model.Profesor;
import com.colegio.msprofesores.repository.ProfesorRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfesorServiceTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorService profesorService;

    @Test
    void guardarProfesorCorrectamente() {

        ProfesorRequestDTO dto = new ProfesorRequestDTO();
        dto.setNombre("Juan");
        dto.setApellido("Pérez");
        dto.setEmail("juan@colegio.cl");
        dto.setEspecialidad("Matemáticas");

        Profesor profesorGuardado = new Profesor(
                1L,
                "Juan",
                "Pérez",
                "juan@colegio.cl",
                "Matemáticas"
        );

        when(profesorRepository.save(any(Profesor.class)))
                .thenReturn(profesorGuardado);

        ProfesorResponseDTO resultado = profesorService.guardar(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan", resultado.getNombre());

        verify(profesorRepository, times(1))
                .save(any(Profesor.class));
    }

    @Test
    void obtenerProfesorPorId() {

        Profesor profesor = new Profesor(
                1L,
                "Juan",
                "Pérez",
                "juan@colegio.cl",
                "Matemáticas"
        );

        when(profesorRepository.findById(1L))
                .thenReturn(Optional.of(profesor));

        ProfesorResponseDTO resultado =
                profesorService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
    }

    @Test
    void obtenerProfesorPorIdNoExiste() {

        when(profesorRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> profesorService.obtenerPorId(99L)
        );

        assertEquals(
                "Profesor no encontrado",
                exception.getMessage()
        );
    }
}