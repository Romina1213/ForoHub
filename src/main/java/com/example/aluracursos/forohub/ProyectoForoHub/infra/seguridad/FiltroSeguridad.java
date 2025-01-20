package com.example.aluracursos.forohub.ProyectoForoHub.infra.seguridad;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;


@Component
public class FiltroSeguridad extends OncePerRequestFilter {
    @Value("${api.security.secret}")
    private String apiSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("SecurityFilter ejecutado para: " + request.getServletPath());

        try {
            String header = request.getHeader("Authorization");

            if (header != null && header.startsWith("Bearer ")) {
                String token = header.replace("Bearer ", "");
                Algorithm algorithm = Algorithm.HMAC256(apiSecret);
                var verifier = JWT.require(algorithm).withIssuer("usuarios").build();
                DecodedJWT decodedToken = verifier.verify(token);

                System.out.println("Token válido para: " + decodedToken.getSubject());
                System.out.println("Claims del token: " + decodedToken.getClaims());

                String username = decodedToken.getSubject();
                if (username != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JWTVerificationException e) {
            System.out.println("Error de validación del token: " + e.getMessage());
        }

        // Continúa con el filtro, independientemente de la autenticación
        filterChain.doFilter(request, response);
    }
}