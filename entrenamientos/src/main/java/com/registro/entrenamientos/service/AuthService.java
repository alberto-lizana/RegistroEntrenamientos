package com.registro.entrenamientos.service;

import com.registro.entrenamientos.dto.AuthResponseDto;
import com.registro.entrenamientos.dto.CodigoRecuperarResponseDto;
import com.registro.entrenamientos.dto.LoginRequestDto;
import com.registro.entrenamientos.dto.RecuperarPasswordRequestDto;
import com.registro.entrenamientos.dto.RestablecerPasswordRequestDto;

public interface AuthService {

    AuthResponseDto loginUsuario(LoginRequestDto dto);

    CodigoRecuperarResponseDto recuperarPassword(RecuperarPasswordRequestDto request);

    void restablecerPassword(RestablecerPasswordRequestDto request);
}
