package com.service;

import com.model.entities.Prestamo;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    List<Prestamo> listAll();
    Optional<Prestamo> findById(String id);
    Prestamo create(Prestamo p);
}
