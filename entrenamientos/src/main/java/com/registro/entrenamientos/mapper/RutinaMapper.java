package com.registro.entrenamientos.mapper;

import com.registro.entrenamientos.dto.RutinaResponseDto;
import com.registro.entrenamientos.model.Rutina;

public class RutinaMapper {

    public static RutinaResponseDto toDto(Rutina rutina){
        return RutinaResponseDto
            .builder()
                .idRutina(rutina.getId())
                .titulo(rutina.getTitulo())
                .tipoRutina(TipoRutinaMapper.toDto(rutina.getTipoRutina()))
            .build();
    }
}
