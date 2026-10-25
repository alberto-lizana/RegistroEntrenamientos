package com.registro.entrenamientos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModificarAppatUsuarioRequestDto (

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(min = 3, max = 50, message = "...")
    String appat

) {}
