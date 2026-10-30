package com.registro.entrenamientos.mapper;

import com.registro.entrenamientos.dto.DiaRutinaResponseDto;
import com.registro.entrenamientos.model.DiaRutina;

public class DiaRutinaMapper {

    public static DiaRutinaResponseDto toDto(DiaRutina diaRutina){
        return DiaRutinaResponseDto
            .builder()
                .idDiaRutina(diaRutina.getId())
                .titulo(diaRutina.getTitulo())
                .nombre(diaRutina.getNombre())
                .orden(diaRutina.getOrden())
                .rutina(RutinaMapper.toDto(diaRutina.getRutina()))
            .build();
    }

}
