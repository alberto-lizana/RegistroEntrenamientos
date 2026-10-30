package com.registro.entrenamientos.mapper;

import com.registro.entrenamientos.dto.EjercicioRutinaResponseDto;
import com.registro.entrenamientos.model.EjercicioRutina;

public class EjercicioRutinaMapper {

    public static EjercicioRutinaResponseDto toDto(EjercicioRutina ejercicioRutina){
        return EjercicioRutinaResponseDto
            .builder()
                .idEjercicioRutina(ejercicioRutina.getId())
                .diaRutina(DiaRutinaMapper.toDto(ejercicioRutina.getDiaRutina()))
                .ejercicio(EjercicioMapper.toDto(ejercicioRutina.getEjercicio()))
                .orden(ejercicioRutina.getOrden())
                .series(ejercicioRutina.getSeries())
                .repeticionesMin(ejercicioRutina.getRepeticionesMin())
                .repeticionesMax(ejercicioRutina.getRepeticionesMax())
                .descansoMin(ejercicioRutina.getDescansoMin())
            .build();
    }

}
