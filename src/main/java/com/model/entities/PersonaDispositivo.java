package com.model.entities;

import java.time.LocalDate;

public class PersonaDispositivo {
    private String id;
    private String personaId;
    private String dispositivoId;
    private LocalDate fechaAsignacion;
    private LocalDate fechaDevolucion;
    private String razon;

    public PersonaDispositivo() { this.fechaAsignacion = LocalDate.now(); }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPersonaId() { return personaId; }
    public void setPersonaId(String personaId) { this.personaId = personaId; }
    public String getDispositivoId() { return dispositivoId; }
    public void setDispositivoId(String dispositivoId) { this.dispositivoId = dispositivoId; }
    public LocalDate getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(LocalDate fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }
    public String getRazon() { return razon; }
    public void setRazon(String razon) { this.razon = razon; }
}

