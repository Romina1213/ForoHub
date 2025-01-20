package com.example.aluracursos.forohub.ProyectoForoHub.dominio.validaciones;

import com.example.aluracursos.forohub.ProyectoForoHub.dominio.topico.DatosGeneralTopico;
import com.example.aluracursos.forohub.ProyectoForoHub.infra.manejoErrores.ValidacionException;
import com.example.aluracursos.forohub.ProyectoForoHub.repositorios.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidacionUnicoNombreDelTopico implements ValidadorNombre{

    @Autowired
    TopicoRepository repository;

    @Override
    public void validar(DatosGeneralTopico datosGeneralTopico) {
        if (repository.findByTitulo(datosGeneralTopico.titulo()).isPresent()){
            throw new ValidacionException("el topico ya existe");
        }
    }

}