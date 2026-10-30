package com.registro.entrenamientos.service;

import java.util.List;

import com.registro.entrenamientos.dto.RutinaResponseDto;

public interface RutinaService {

    List<RutinaResponseDto> obtenerTodas();
    RutinaResponseDto obtenerPorId(Long id);

}
