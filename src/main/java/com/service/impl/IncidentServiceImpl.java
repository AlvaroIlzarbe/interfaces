package com.service.impl;

import com.model.DataStore;
import com.model.entities.Averia;
import com.service.IncidentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class IncidentServiceImpl implements IncidentService {
    private final DataStore store;

    public IncidentServiceImpl(DataStore store) { this.store = store; }

    @Override
    public List<Averia> listAll() { return store.getAverias(); }

    @Override
    public Optional<Averia> findById(String id) { return store.findAveriaById(id); }

    @Override
    public Averia create(Averia a) {
        store.addAveria(a);
        return a;
    }

    @Override
    public void close(Averia a, String solucion) {
        a.setSolucion(solucion);
        a.setEstado(Averia.Estado.CERRADO);
        a.setFechaCierre(LocalDateTime.now());
        store.saveAll();
    }
}
