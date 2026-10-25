package com.registro.entrenamientos.dto;

import com.registro.entrenamientos.enums.SexoEnum;

import jakarta.validation.constraints.NotNull;

public record ModificarSexoUsuarioRequestDto (

    @NotNull(message = "El sexo es obligatorio")
    SexoEnum sexo

) {}
