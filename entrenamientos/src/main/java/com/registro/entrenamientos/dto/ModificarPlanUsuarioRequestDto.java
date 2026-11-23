package com.registro.entrenamientos.dto;

import com.registro.entrenamientos.enums.PlanEnum;

import jakarta.validation.constraints.NotNull;

public record ModificarPlanUsuarioRequestDto (

    @NotNull(message = "El id del usuario es obligatorio")
    Long idUsuario,
    
    @NotNull(message = "El plan es obligatorio")
    PlanEnum plan

) {}
