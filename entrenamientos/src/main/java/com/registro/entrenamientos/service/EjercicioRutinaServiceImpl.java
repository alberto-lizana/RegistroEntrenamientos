package com.registro.entrenamientos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.registro.entrenamientos.dto.EjercicioRutinaResponseDto;
import com.registro.entrenamientos.dto.RutinaCompletaResponseDto;
import com.registro.entrenamientos.mapper.EjercicioRutinaMapper;
import com.registro.entrenamientos.repository.EjercicioRutinaRepository;

@Service 
public class EjercicioRutinaServiceImpl implements EjercicioRutinaService {

    private final EjercicioRutinaRepository ejercicioRutinaRepository;

    public EjercicioRutinaServiceImpl(EjercicioRutinaRepository ejercicioRutinaRepository){
        this.ejercicioRutinaRepository = ejercicioRutinaRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RutinaCompletaResponseDto> obtenerTodos() {
        return ejercicioRutinaRepository.obtenerTodos();
    }

    @Transactional(readOnly = true)
    @Override
    public List<RutinaCompletaResponseDto> ejerciciosDelDia(Long id) {
        return ejercicioRutinaRepository.ejerciciosDelDia(id);
    }

    @Override
    public List<EjercicioRutinaResponseDto> ejerciciosDelDiaJerarquizado(Long idDia, Long idRutina) {
        return ejercicioRutinaRepository.ejerciciosDelDiaJerarquizado(idDia, idRutina)
            .stream()
            .map(EjercicioRutinaMapper::toDto)
            .toList();
    }

}
