package com.example.aluracursos.forohub.ProyectoForoHub.infra.seguridad;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.aluracursos.forohub.ProyectoForoHub.dominio.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class Token {
    @Value("${api.security.secret}")
    private String apiSecret;


    public String obtenerToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("usuarios")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al crear el token JWT", exception);
        }
    }


    public String getSubject(String token){
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("usuarios")
                    .build()
                    .verify(token);
            verifier.getSubject();

        } catch (JWTVerificationException exception){

        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("verifier invalido");
        }
        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion(){
        // Usa ZoneOffset.ofHours para evitar el error con caracteres no numéricos
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.ofHours(-5)); // -5 horas de diferencia
    }
}