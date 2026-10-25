package com.registro.entrenamientos.service;

import com.registro.entrenamientos.dto.ModificarApmatUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarAppatUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarFechaNacimientoUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarNombreUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarPasswordUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarPlanUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarSexoUsuarioRequestDto;
import com.registro.entrenamientos.dto.RegistrarRequestDto;
import com.registro.entrenamientos.dto.UsuarioResponseDto;

public interface UsuarioService {

    UsuarioResponseDto registrarUsuario(RegistrarRequestDto request);

    UsuarioResponseDto obtener();

    UsuarioResponseDto modificarNombre(ModificarNombreUsuarioRequestDto dto);

    UsuarioResponseDto modificarAppat(ModificarAppatUsuarioRequestDto dto);

    UsuarioResponseDto modificarApmat(ModificarApmatUsuarioRequestDto dto);

    UsuarioResponseDto modificarFechaNacimiento(ModificarFechaNacimientoUsuarioRequestDto dto);

    UsuarioResponseDto modificarPassword(ModificarPasswordUsuarioRequestDto dto);

    UsuarioResponseDto modificarPlan(ModificarPlanUsuarioRequestDto dto);
    
    UsuarioResponseDto modificarSexo(ModificarSexoUsuarioRequestDto dto);

    void borradoLogico(Long id);

    void borradoFisico(Long id);
}
