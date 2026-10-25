package com.registro.entrenamientos.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/* 
    Verifica firma y vigencia 
        Esta clase concentra las operaciones criptográficas relacionadas con tus tokens.
        No consulta usuarios, no decide qué endpoints son públicos y no establece por sí sola una sesión autenticada. 
        Su responsabilidad es trabajar con el JWT.

    Header
    Indica el tipo de token y el algoritmo de firma.

    Payload
    Contiene claims, por ejemplo, el ID del usuario y las fechas de emisión y expiración.

    Signature
    Permite comprobar que el contenido no se ha modificado y que se utilizó la clave correcta.

    ¿Qué problema resuelve el JWT?
    En una aplicación web sin JWT. Una vez que el usuario inicia sesión, 
    el servidor tiene que mantener una sesión y recordar la identidad autenticada.
    En una API sin estado, tu aplicación puede evitar esa sesión persistente.
    El servidor entrega un JWT y el cliente lo presenta en las siguientes peticiones.
    El servidor verifica el token para decidir si puede reconocer esa identidad.
    
    Esto no elimina la necesidad de proteger el token: 
    cualquiera que lo robe podría intentar utilizarlo mientras sea válido. 
    Por eso se debe transmitir por HTTPS, evitar filtrarlo en logs y 
    almacenarlo en el frontend de acuerdo con un modelo de seguridad apropiado.

    @Component: permite que Spring cree y administre una instancia de la clase.
    @Value("${jwt.secret}"): obtiene la propiedad jwt.secret de la configuración de Spring.
    SecretKey: representa la clave criptográfica.
    Keys.hmacShaKeyFor(...): construye una clave compatible con los algoritmos HMAC admitidos por JJWT.

        public static JwtBuilder builder() {
        return Classes.newInstance("io.jsonwebtoken.impl.DefaultJwtBuilder");
    }

    io.jsonwebtoken.impl.DefaultJwtBuilder = 
        Es una técnica habitual en bibliotecas Java: 
            una API pública ofrece métodos estables y oculta detalles de construcción.


    generarToken(): Crea y firma un JWT
        subject	Identidad del usuario: en tu implementación, el ID. (Define el claim estándar sub).
        issuedAt Momento en que se emitió el token. (Define iat).
        expiration Momento en que deja de ser válido. (Define exp).
        signWith se conecta con el secreto con hmacShaKeyFor. (Establece cómo se firma el token).
        compact(): La forma de entenderlo es que estás construyendo un objeto que representa un JWT y, 
            al final, lo conviertes en la cadena que enviarás al cliente. (Finaliza el proceso y produce la cadena serializada).


        compact() es el paso final. No es el método que autentica al usuario ni el que verifica una contraseña.
        En términos conceptuales, realiza el proceso de serialización y firma para generar el JWT compacto:
            1. Toma los claims que configuramos.
            2. Prepara el encabezado del JWT.
            3. Serializa el encabezado y el contenido como JSON.
            4. Codifica las partes con Base64URL.
            5. Genera la firma criptográfica con la clave configurada.
            6. Devuelve la cadena en el formato header.payload.signature.

    extraerId(): Verifica y analiza el token para recuperar el ID

    esTokenValido(): Comprueba que el token corresponde al usuario esperado


*/
@Component
public class JwtService {

    private final SecretKey key;

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generarToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60) * 12)) // Token durará 12 horas
                .signWith(this.key)
                .compact();
    }

    public String extraerId(String token) {
        return Jwts.parser()
                .verifyWith(this.key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean esTokenValido(String token, UserDetails userDetails) {
        try {
            String id = extraerId(token);

            Claims claims = Jwts.parser()
                    .verifyWith(this.key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            boolean noExpirado = claims.getExpiration().after(new Date());

            return id.equals(userDetails.getUsername()) && noExpirado;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}