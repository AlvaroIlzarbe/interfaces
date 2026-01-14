package com.model.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Averia {
    public enum Estado {ABIERTO, EN_PROGRESO, CERRADO}

    private final String id;
    private String dispositivoId;
    private String personaReportaId;
    private String tipo;
    private String descripcion;
    private LocalDateTime fechaReporte;
    private LocalDateTime fechaCierre;
    private Estado estado;
    private String solucion;
    private String tecnicoAsignadoId;

    public Averia() {
        this.id = UUID.randomUUID().toString();
        this.fechaReporte = LocalDateTime.now();
        this.estado = Estado.ABIERTO;
    }

    public String getId() { return id; }
    public String getDispositivoId() { return dispositivoId; }
    public void setDispositivoId(String dispositivoId) { this.dispositivoId = dispositivoId; }
    public String getPersonaReportaId() { return personaReportaId; }
    public void setPersonaReportaId(String personaReportaId) { this.personaReportaId = personaReportaId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDateTime getFechaReporte() { return fechaReporte; }
    public LocalDateTime getFechaCierre() { return fechaCierre; }
    public void setFechaCierre(LocalDateTime fechaCierre) { this.fechaCierre = fechaCierre; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public String getSolucion() { return solucion; }
    public void setSolucion(String solucion) { this.solucion = solucion; }
    public String getTecnicoAsignadoId() { return tecnicoAsignadoId; }
    public void setTecnicoAsignadoId(String tecnicoAsignadoId) { this.tecnicoAsignadoId = tecnicoAsignadoId; }

    @Override
    public String toString() {
        return "Averia " + id + " - " + (descripcion != null ? descripcion : "") + " (" + estado + ")";
    }
}

