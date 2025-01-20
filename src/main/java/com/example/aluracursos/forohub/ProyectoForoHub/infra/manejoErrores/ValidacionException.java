package com.example.aluracursos.forohub.ProyectoForoHub.infra.manejoErrores;

public class ValidacionException extends RuntimeException {
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
