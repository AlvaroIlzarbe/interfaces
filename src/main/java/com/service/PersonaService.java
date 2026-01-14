package com.service;

import com.model.entities.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
    List<Persona> listAll();
    Optional<Persona> findById(String id);
    Persona create(Persona p);
}
