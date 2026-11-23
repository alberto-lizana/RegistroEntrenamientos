package com.registro.entrenamientos.dto;

import com.registro.entrenamientos.enums.RolEnum;

import jakarta.validation.constraints.NotNull;

public record ModificarRolUsuarioRequestDto(

    @NotNull(message = "El id del usuario es obligatorio")
    Long idUsuario,

    @NotNull(message = "El rol es obligatorio")
    RolEnum rol
    
) {}