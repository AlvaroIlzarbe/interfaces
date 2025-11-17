package org.example.model;

import java.time.LocalDate;

public class Tarea {
    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private String responsable;

    public Tarea(String nombre, String descripcion, LocalDate fecha, String responsable) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.responsable = responsable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", responsable='" + responsable + '\'' +
                '}';
    }
}
