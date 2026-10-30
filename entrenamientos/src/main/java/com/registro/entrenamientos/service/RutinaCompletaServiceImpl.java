package com.registro.entrenamientos.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.registro.entrenamientos.dto.EjercicioRutinaNativaResponseDto;
import com.registro.entrenamientos.dto.RutinaCompletaNativaResponseDto;
import com.registro.entrenamientos.dto.RutinaCompletaResponseDto;
import com.registro.entrenamientos.dto.projection.RutinaCompletaNativaProjection;
import com.registro.entrenamientos.exception.RutinaNoEncontradaException;
import com.registro.entrenamientos.repository.RutinaRepository;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Slf4j 
@Service 
public class RutinaCompletaServiceImpl implements RutinaCompletaService {

    private final RutinaRepository rutinaRepository;
    private final ObjectMapper objectMapper;

    public RutinaCompletaServiceImpl(RutinaRepository rutinaRepository, ObjectMapper objectMapper){
        this.rutinaRepository = rutinaRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<RutinaCompletaResponseDto> obtenerTodasRutinaCompleta() {
        return rutinaRepository.obtenerTodasRutinaCompleta();
    }

    @Transactional(readOnly = true)
    @Override
    public List<RutinaCompletaResponseDto> obtenerRutinaCompletaPorId(Long id) {
        return rutinaRepository.obtenerTodasRutinaCompleta(id);
    }

    @Transactional(readOnly = true)
    @Override
    public RutinaCompletaNativaResponseDto obtenerPorRutinaId(Long idRutina) {

        RutinaCompletaNativaProjection resultado = rutinaRepository.obtenerRutinaCompletaNativa(idRutina)
            .orElseThrow(() -> new RutinaNoEncontradaException("Rutina no encontrada"));

        try {
            log.info("1. Proyección obtenida");
            log.info("3. Llegamos antes de leer el CLOB");

            String json = resultado.getEjercicios().getSubString(1,(int) resultado.getEjercicios().length());

            log.info("4. JSON RECIBIDO: " + json);

            List<EjercicioRutinaNativaResponseDto> ejercicios =
                objectMapper.readValue(json, new TypeReference<List<EjercicioRutinaNativaResponseDto>>() {});

            log.info("5. JSON convertido correctamente");

            return RutinaCompletaNativaResponseDto.builder()
                .rutinaTitulo(resultado.getRutinaTitulo())
                .nombreTipoRutina(resultado.getNombreTipoRutina())
                .ejercicios(ejercicios)
                .build();

        } catch (SQLException | JacksonException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al procesar los ejercicios de la rutina", e);
        }
    }
    


}

