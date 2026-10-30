package com.registro.entrenamientos.dto.projection;

import java.sql.Clob;

public interface RutinaCompletaNativaProjection {

    String getRutinaTitulo();

    String getNombreTipoRutina();

    Clob getEjercicios();

}