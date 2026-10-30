package com.registro.entrenamientos.service;

import java.util.List;

import com.registro.entrenamientos.dto.RutinaCompletaNativaResponseDto;
import com.registro.entrenamientos.dto.RutinaCompletaResponseDto;

public interface RutinaCompletaService {

    List<RutinaCompletaResponseDto> obtenerTodasRutinaCompleta();

    List<RutinaCompletaResponseDto> obtenerRutinaCompletaPorId(Long id);

    RutinaCompletaNativaResponseDto obtenerPorRutinaId(Long idRutina);

}