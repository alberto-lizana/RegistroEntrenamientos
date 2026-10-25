package com.registro.entrenamientos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registro.entrenamientos.dto.ModificarApmatUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarAppatUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarFechaNacimientoUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarNombreUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarPasswordUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarPlanUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarSexoUsuarioRequestDto;
import com.registro.entrenamientos.dto.RegistrarRequestDto;
import com.registro.entrenamientos.dto.UsuarioResponseDto;
import com.registro.entrenamientos.service.UsuarioService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/obtener")
    public ResponseEntity<UsuarioResponseDto> obtener() {

        UsuarioResponseDto response = usuarioService.obtener();

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponseDto> register(@Valid @RequestBody RegistrarRequestDto request) {

        UsuarioResponseDto response = usuarioService.registrarUsuario(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/modificar-nombre")
    public ResponseEntity<UsuarioResponseDto> modificarNombre(@Valid @RequestBody ModificarNombreUsuarioRequestDto dto){
        UsuarioResponseDto response = usuarioService.modificarNombre(dto);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/modificar-appat")
    public ResponseEntity<UsuarioResponseDto> modificarAppat(@Valid @RequestBody ModificarAppatUsuarioRequestDto dto){
        UsuarioResponseDto response = usuarioService.modificarAppat(dto);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/modificar-apmat")
    public ResponseEntity<UsuarioResponseDto> modificarApmat(@Valid @RequestBody ModificarApmatUsuarioRequestDto dto){
        UsuarioResponseDto response = usuarioService.modificarApmat(dto);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/modificar-fechaNacimiento")
    public ResponseEntity<UsuarioResponseDto> modificarFechaNacimiento(@Valid @RequestBody ModificarFechaNacimientoUsuarioRequestDto dto){
        UsuarioResponseDto response = usuarioService.modificarFechaNacimiento(dto);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/modificar-password")
    public ResponseEntity<UsuarioResponseDto> modificarPassword(@Valid @RequestBody ModificarPasswordUsuarioRequestDto dto){
        UsuarioResponseDto response = usuarioService.modificarPassword(dto);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/modificar-plan")
    public ResponseEntity<UsuarioResponseDto> modificarPlan(@Valid @RequestBody ModificarPlanUsuarioRequestDto dto){
        UsuarioResponseDto response = usuarioService.modificarPlan(dto);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/modificar-sexo")
    public ResponseEntity<UsuarioResponseDto> modificarSexo(@Valid @RequestBody ModificarSexoUsuarioRequestDto dto){
        UsuarioResponseDto response = usuarioService.modificarSexo(dto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/borrado-logico/{id}")
    public ResponseEntity<Void> borradoLogico(@PathVariable Long id){
        usuarioService.borradoLogico(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/borrado-fisico/{id}")
    public ResponseEntity<Void> borradoFisico(@PathVariable Long id){
        usuarioService.borradoFisico(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
