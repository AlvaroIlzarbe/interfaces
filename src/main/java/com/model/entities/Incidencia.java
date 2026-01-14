package com.model.entities;

import java.time.LocalDateTime;

public class Incidencia {
    public enum Estado {ABIERTA, EN_PROGRESO, CERRADA}

    private static int COUNTER = 1;
    private final int id;
    private String descripcion;
    private Estado estado;
    private String asignado;
    private LocalDateTime fecha;

    public Incidencia(String descripcion) {
        this.id = COUNTER++;
        this.descripcion = descripcion;
        this.estado = Estado.ABIERTA;
        this.fecha = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public String getAsignado() { return asignado; }
    public void setAsignado(String asignado) { this.asignado = asignado; }
    public LocalDateTime getFecha() { return fecha; }

    @Override
    public String toString() { return "Incidencia #" + id + " - " + descripcion + " (" + estado + ")"; }
}

