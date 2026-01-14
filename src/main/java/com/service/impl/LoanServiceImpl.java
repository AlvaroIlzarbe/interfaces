package com.service.impl;

import com.model.DataStore;
import com.model.entities.Prestamo;
import com.service.LoanService;

import java.util.List;
import java.util.Optional;

public class LoanServiceImpl implements LoanService {
    private final DataStore store;

    public LoanServiceImpl(DataStore store) { this.store = store; }

    @Override
    public List<Prestamo> listAll() { return store.getPrestamos(); }

    @Override
    public Optional<Prestamo> findById(String id) {
        if (id == null) return Optional.empty();
        try {
            int iid = Integer.parseInt(id);
            return store.getPrestamos().stream().filter(p -> p.getId() == iid).findFirst();
        } catch (NumberFormatException ex) {
            return store.getPrestamos().stream().filter(p -> String.valueOf(p.getId()).equals(id)).findFirst();
        }
    }

    @Override
    public Prestamo create(Prestamo p) { store.addPrestamo(p); return p; }
}
