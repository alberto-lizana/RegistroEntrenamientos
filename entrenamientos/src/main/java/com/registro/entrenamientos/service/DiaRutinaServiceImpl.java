package com.registro.entrenamientos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.registro.entrenamientos.dto.DiaRutinaResponseDto;
import com.registro.entrenamientos.exception.DiaRutinaNoEncontradoException;
import com.registro.entrenamientos.mapper.DiaRutinaMapper;
import com.registro.entrenamientos.repository.DiaRutinaRepository;

@Service 
public class DiaRutinaServiceImpl implements DiaRutinaService {

    private final DiaRutinaRepository diaRutinaRepository;

    public DiaRutinaServiceImpl(DiaRutinaRepository diaRutinaRepository){
        this.diaRutinaRepository = diaRutinaRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DiaRutinaResponseDto> obtenerTodos() {
        return diaRutinaRepository.obtenerTodos()
            .stream()
            .map(DiaRutinaMapper::toDto)
            .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public DiaRutinaResponseDto obtenerPorId(Long id) {
        return DiaRutinaMapper.toDto(diaRutinaRepository.obtenerPorId(id)
            .orElseThrow(() -> new DiaRutinaNoEncontradoException("No se ha encontrado el día de rutina")));
    }

}
