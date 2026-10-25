package com.registro.entrenamientos.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/*
    Función pequeña pero potente. Nos permite obtener el id de un usuario a través de su token. 
    Así logramos crear una función reutilizable con una función que requiere la aplicación 
    para obtener el id del usuario y así poder consultar suis datos y, en caso de solo requerir su id, dejarlo asi.

    La ventaja es que el servicio no necesita recibir el ID del usuario como argumento desde el controlador. 
    Puede obtener la identidad desde el contexto autenticado.

*/
@Component
public class UsuarioAutenticadoService {

    public Long obtenerUsuarioIdAutenticado() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return Long.valueOf(authentication.getName());
    }
}
