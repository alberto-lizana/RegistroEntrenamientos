package com.registro.entrenamientos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registro.entrenamientos.dto.AuthResponseDto;
import com.registro.entrenamientos.dto.CodigoRecuperarResponseDto;
import com.registro.entrenamientos.dto.LoginRequestDto;
import com.registro.entrenamientos.dto.RecuperarPasswordRequestDto;
import com.registro.entrenamientos.dto.RestablecerPasswordRequestDto;
import com.registro.entrenamientos.service.AuthService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController 
@RequestMapping("/auth")
public class AuthController  {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        log.info("Solicitud recibida para iniciar sesión");
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.loginUsuario(request));
    }

    @PostMapping("/recuperar")
    public ResponseEntity<CodigoRecuperarResponseDto> recuperarPassword(@Valid @RequestBody RecuperarPasswordRequestDto request) {
        log.info("Solicitud recibida para recuperar contraseña");
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.recuperarPassword(request));
    }

    @PostMapping("/restablecer")
    public ResponseEntity<Void> restablecerPassword(@Valid @RequestBody RestablecerPasswordRequestDto request) {
        log.info("Solicitud recibida para restablecer contraseña");
        authService.restablecerPassword(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}