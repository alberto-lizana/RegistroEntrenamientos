package com.registro.entrenamientos.service;

import java.util.List;

import com.registro.entrenamientos.dto.EjercicioRutinaResponseDto;
import com.registro.entrenamientos.dto.RutinaCompletaResponseDto;

public interface EjercicioRutinaService {

    List<RutinaCompletaResponseDto> obtenerTodos();

    List<RutinaCompletaResponseDto> ejerciciosDelDia(Long id);

    List<EjercicioRutinaResponseDto> ejerciciosDelDiaJerarquizado(Long idDia, Long idRutina);

}
