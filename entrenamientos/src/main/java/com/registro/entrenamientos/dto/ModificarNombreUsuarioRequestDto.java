package com.registro.entrenamientos.dto;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;

public record ModificarNombreUsuarioRequestDto (

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
    String nombre

) {}
