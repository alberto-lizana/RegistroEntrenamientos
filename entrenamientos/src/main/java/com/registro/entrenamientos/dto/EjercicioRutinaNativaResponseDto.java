package com.registro.entrenamientos.dto;

import lombok.Builder;

@Builder
public record EjercicioRutinaNativaResponseDto(
    int orden,
    String nombre,
    Integer series,
    Integer repeticionesMin,
    Integer repeticionesMax,
    Double descanso
) {}