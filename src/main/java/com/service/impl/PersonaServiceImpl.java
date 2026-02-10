package com.service.impl;

import com.model.DataStore;
import com.model.entities.Persona;
import com.service.PersonaService;

import java.util.List;
import java.util.Optional;

public class PersonaServiceImpl implements PersonaService {
    private final DataStore store;

    public PersonaServiceImpl(DataStore store) { this.store = store; }

    @Override
    public List<Persona> listAll() { return store.getPersonas(); }

    @Override
    public Optional<Persona> findById(String id) { return store.findPersonaById(id); }

    @Override
    public Persona create(Persona p) {
        store.addPersona(p);
        return p;
    }
}
