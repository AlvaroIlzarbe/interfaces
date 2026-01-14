package com.service.impl;

import com.model.DataStore;
import com.model.entities.Averia;
import com.model.entities.Prestamo;
import com.service.ReportService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReportServiceImpl implements ReportService {
    private final DataStore store;

    public ReportServiceImpl(DataStore store) { this.store = store; }

    @Override
    public List<Prestamo> filterPrestamos(String persona, LocalDate desde, LocalDate hasta) {
        String pLower = persona == null ? "" : persona.toLowerCase();
        return store.getPrestamos().stream()
                .filter(p -> p.getSolicitante().toLowerCase().contains(pLower))
                .filter(p -> {
                    if (desde != null && p.getFecha().toLocalDate().isBefore(desde)) return false;
                    if (hasta != null && p.getFecha().toLocalDate().isAfter(hasta)) return false;
                    return true;
                }).collect(Collectors.toList());
    }

    @Override
    public List<Averia> filterAverias(String persona, LocalDate desde, LocalDate hasta) {
        String pLower = persona == null ? "" : persona.toLowerCase();
        return store.getAverias().stream()
                .filter(a -> {
                    if (a.getPersonaReportaId() == null) return false;
                    return store.findPersonaById(a.getPersonaReportaId())
                            .map(per -> (per.getNombre() + " " + per.getApellido()).toLowerCase().contains(pLower)).orElse(false);
                })
                .filter(a -> {
                    if (desde != null && a.getFechaReporte().toLocalDate().isBefore(desde)) return false;
                    if (hasta != null && a.getFechaReporte().toLocalDate().isAfter(hasta)) return false;
                    return true;
                }).collect(Collectors.toList());
    }
}
