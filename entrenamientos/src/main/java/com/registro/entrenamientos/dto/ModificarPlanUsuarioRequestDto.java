package com.registro.entrenamientos.dto;

import com.registro.entrenamientos.enums.PlanEnum;

import jakarta.validation.constraints.NotNull;

public record ModificarPlanUsuarioRequestDto (

    @NotNull(message = "El plan es obligatorio")
    PlanEnum plan

) {}
