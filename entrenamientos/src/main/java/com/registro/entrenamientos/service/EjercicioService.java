package com.registro.entrenamientos.service;

import java.util.List;

import com.registro.entrenamientos.dto.EjercicioResponseDto;

public interface EjercicioService {

    List<EjercicioResponseDto> obtenerTodos();
    EjercicioResponseDto obtenerPorId(Long id);
    
}
