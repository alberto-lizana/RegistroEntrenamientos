package com.registro.entrenamientos.mapper;

import com.registro.entrenamientos.dto.TipoRutinaResponseDto;
import com.registro.entrenamientos.model.TipoRutina;

public class TipoRutinaMapper {

    public static TipoRutinaResponseDto toDto(TipoRutina tipoRutina) {
        return TipoRutinaResponseDto
            .builder()
                .idTipoRutina(tipoRutina.getId())
                .nombre(tipoRutina.getNombre())
                .diasDescanso(tipoRutina.getDiasDescanso())
                .diasPorSemana(tipoRutina.getDiasPorSemana())
                .descripcion(tipoRutina.getDescripcion())
            .build();
    }
    
}
