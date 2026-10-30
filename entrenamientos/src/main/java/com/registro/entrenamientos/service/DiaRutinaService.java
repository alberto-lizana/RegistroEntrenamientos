package com.registro.entrenamientos.service;

import java.util.List;

import com.registro.entrenamientos.dto.DiaRutinaResponseDto;

public interface DiaRutinaService {

    List<DiaRutinaResponseDto> obtenerTodos();
    DiaRutinaResponseDto obtenerPorId(Long id);

}
