package com.colegio.msprofesores.dto;

import lombok.Data;

@Data
public class ProfesorResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String especialidad;
}