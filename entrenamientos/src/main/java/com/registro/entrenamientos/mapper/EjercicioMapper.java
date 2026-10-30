package com.registro.entrenamientos.mapper;

import com.registro.entrenamientos.dto.EjercicioResponseDto;
import com.registro.entrenamientos.model.Ejercicio;

public class EjercicioMapper {

    public static EjercicioResponseDto toDto(Ejercicio ejercicio){
        return EjercicioResponseDto
        .builder()
            .id(ejercicio.getId())
            .nombre(ejercicio.getNombre())
        .build();
    }

}
