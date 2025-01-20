package com.example.aluracursos.forohub.ProyectoForoHub.dominio.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "topico")
@Entity
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaDeCreacion;
    private Estado estado;//este atributo es un enum
    private String autor;
    private String curso;

    public Topico (){}

    public Topico(@Valid DatosGeneralTopico datosGeneralTopico) {
        this.titulo = datosGeneralTopico.titulo();
        this.mensaje = datosGeneralTopico.mensaje();
        this.autor = datosGeneralTopico.autor();
        this.curso = datosGeneralTopico.curso();
        this.fechaDeCreacion = datosGeneralTopico.fechaDeCreacion();
        this.estado = datosGeneralTopico.estado();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }

    public void informacionActualizada(@Valid DatosActualizarTopico datos) {
        if (datos.titulo() != null){
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }
        if (datos.autor() != null){
            this.autor = datos.autor();
        }
        if (datos.estadoActualizado() != null){
            this.estado = datos.estadoActualizado();
        }
    }
}
