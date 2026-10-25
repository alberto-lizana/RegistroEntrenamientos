package com.registro.entrenamientos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModificarApmatUsuarioRequestDto(

    @NotBlank(message = "El apellido materno es obligatorio")
    @Size(max = 50, message = "El apellido materno no puede superar los 50 caracteres")
    String apmat

) {}
