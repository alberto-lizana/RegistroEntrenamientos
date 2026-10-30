package com.registro.entrenamientos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.registro.entrenamientos.dto.EjercicioResponseDto;
import com.registro.entrenamientos.exception.EjercicioNoEncontadoException;
import com.registro.entrenamientos.mapper.EjercicioMapper;
import com.registro.entrenamientos.repository.EjercicioRepository;

@Service 
public class EjercicioServiceImpl implements EjercicioService {

    private final EjercicioRepository ejercicioRepository;

    public EjercicioServiceImpl(EjercicioRepository ejercicioRepository){
        this.ejercicioRepository = ejercicioRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EjercicioResponseDto> obtenerTodos() {
        return ejercicioRepository.obtenerTodos()
            .stream()
            .map(EjercicioMapper::toDto)
            .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public EjercicioResponseDto obtenerPorId(Long id) {
        return EjercicioMapper.toDto(ejercicioRepository.obtenerEjercicioPorId(id)
            .orElseThrow(() -> new EjercicioNoEncontadoException("No se ha encontrado el ejercicio")));
    }

}
