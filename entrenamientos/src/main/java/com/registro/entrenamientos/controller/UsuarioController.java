package com.registro.entrenamientos.controller;

import java.util.List;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestController 
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/obtener")
    public ResponseEntity<UsuarioResponseDto> obtener() {
        log.info("Solicitud recibida para obtener los datos del usuario consultante");
        return ResponseEntity.ok().body(usuarioService.obtener());
    }

    @GetMapping("/obtener-todos")
    public ResponseEntity<List<UsuarioResponseDto>> obtenerTodos() {
        log.info("Solicitud recibida para obtener todos los usuarios con sus datos");
        return ResponseEntity.ok().body(usuarioService.obtenerTodos());
    }

    @GetMapping("/obtener-{id}")
    ResponseEntity<UsuarioResponseDto> obtenerPorId(@PathVariable Long id){
        log.info("Solicitud recibida para obtener los datos de un usuario");
        return ResponseEntity.ok().body(usuarioService.obtenerPorId(id));
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponseDto> register(@Valid @RequestBody RegistrarRequestDto request) {
        log.info("Solicitud recibida para inicar el proceso de crear un nuevo usuario");
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrarUsuario(request));
    }

    @PatchMapping("/modificar-nombre")
    public ResponseEntity<UsuarioResponseDto> modificarNombre(@Valid @RequestBody ModificarNombreUsuarioRequestDto dto){
        log.info("Solicitud recibida para modificar el nombre del usuario consultate");
        return ResponseEntity.ok().body(usuarioService.modificarNombre(dto));
    }

    @PatchMapping("/modificar-appat")
    public ResponseEntity<UsuarioResponseDto> modificarAppat(@Valid @RequestBody ModificarAppatUsuarioRequestDto dto){
        log.info("Solicitud recibida para modificar el apellido paterno del usuario consultate");
        return ResponseEntity.ok().body(usuarioService.modificarAppat(dto));
    }

    @PatchMapping("/modificar-apmat")
    public ResponseEntity<UsuarioResponseDto> modificarApmat(@Valid @RequestBody ModificarApmatUsuarioRequestDto dto){
        log.info("Solicitud recibida para modificar el apellido materno del usuario consultate");
        return ResponseEntity.ok().body(usuarioService.modificarApmat(dto));
    }

    @PatchMapping("/modificar-fechaNacimiento")
    public ResponseEntity<UsuarioResponseDto> modificarFechaNacimiento(@Valid @RequestBody ModificarFechaNacimientoUsuarioRequestDto dto){
        log.info("Solicitud recibida para modifica la fecha de nacimiento del usuario consultate");
        return ResponseEntity.ok().body(usuarioService.modificarFechaNacimiento(dto));
    }

    @PatchMapping("/modificar-password")
    public ResponseEntity<UsuarioResponseDto> modificarPassword(@Valid @RequestBody ModificarPasswordUsuarioRequestDto dto){
        log.info("Solicitud recibida para modificarla contraseña del usuario consultate");
        return ResponseEntity.ok().body(usuarioService.modificarPassword(dto));
    }

    @PatchMapping("/modificar-plan")
    public ResponseEntity<UsuarioResponseDto> modificarPlan(@Valid @RequestBody ModificarPlanUsuarioRequestDto dto){
        log.info("Solicitud recibida para modificar el plan del usuario consultate");
        return ResponseEntity.ok().body(usuarioService.modificarPlan(dto));
    }

    @PatchMapping("/modificar-sexo")
    public ResponseEntity<UsuarioResponseDto> modificarSexo(@Valid @RequestBody ModificarSexoUsuarioRequestDto dto){
        log.info("Solicitud recibida para modificar el sexo del usuario consultate");
        return ResponseEntity.ok().body(usuarioService.modificarSexo(dto));
    }

    @DeleteMapping("/borrado-logico/{id}")
    public ResponseEntity<Void> borradoLogico(@PathVariable Long id){
        log.info("Solicitud recibida para borrar un usuario de forma lógica");
        usuarioService.borradoLogico(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/borrado-fisico/{id}")
    public ResponseEntity<Void> borradoFisico(@PathVariable Long id){
        log.info("Solicitud recibida para borrar un usuario de forma física");
        usuarioService.borradoFisico(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
