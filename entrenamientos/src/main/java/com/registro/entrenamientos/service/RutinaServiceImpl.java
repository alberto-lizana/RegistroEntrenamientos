package com.registro.entrenamientos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.registro.entrenamientos.dto.RutinaResponseDto;
import com.registro.entrenamientos.exception.RutinaNoEncontradaException;
import com.registro.entrenamientos.mapper.RutinaMapper;
import com.registro.entrenamientos.repository.RutinaRepository;

@Service 
public class RutinaServiceImpl implements RutinaService{

    private final RutinaRepository rutinaRepository;

    public RutinaServiceImpl(RutinaRepository rutinaRepository){
        this.rutinaRepository = rutinaRepository;
    }
    @Transactional(readOnly = true)
    @Override
    public List<RutinaResponseDto> obtenerTodas() {
        return rutinaRepository.obtenerTodas()
            .stream()
            .map(RutinaMapper::toDto)
            .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public RutinaResponseDto obtenerPorId(Long id) {
        return rutinaRepository.obtenerPorId(id)
            .map(RutinaMapper::toDto)
            .orElseThrow(() -> new RutinaNoEncontradaException("No se ha encontrado la rutina"));
    }

}
