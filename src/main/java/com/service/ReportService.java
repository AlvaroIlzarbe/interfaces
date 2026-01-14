package com.service;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<com.model.entities.Prestamo> filterPrestamos(String persona, LocalDate desde, LocalDate hasta);
    List<com.model.entities.Averia> filterAverias(String persona, LocalDate desde, LocalDate hasta);
}
