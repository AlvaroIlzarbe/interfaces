package com.service;

import com.model.entities.Averia;

import java.util.List;
import java.util.Optional;

public interface IncidentService {
    List<Averia> listAll();
    Optional<Averia> findById(String id);
    Averia create(Averia a);
    void close(Averia a, String solucion);
}
