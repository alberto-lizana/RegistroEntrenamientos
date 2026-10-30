package com.registro.entrenamientos.mapper;

import com.registro.entrenamientos.dto.UsuarioResponseDto;
import com.registro.entrenamientos.model.Usuario;

public class UsuarioMapper {

    public static UsuarioResponseDto toDto(Usuario usuario){
        return UsuarioResponseDto
            .builder()
                .idUsuario(usuario.getId())
                .nombre(usuario.getNombre())
                .appat(usuario.getAppat())
                .apmat(usuario.getApmat())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .email(usuario.getEmail())
                .plan(usuario.getPlan().name())
                .nombreRol(usuario.getRol().name())
                .sexo(usuario.getSexo().name().toLowerCase())
            .build();
    }

}
