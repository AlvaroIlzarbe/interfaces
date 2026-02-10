package com.model.entities;

import java.util.UUID;

public class Modelo {
    private String id;
    private String fabricante;
    private String nombre;
    private String descripcion;
    private com.model.enums.DeviceType categoria;

    public Modelo() { this.id = UUID.randomUUID().toString(); }

    public String getId() { return id; }
    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public com.model.enums.DeviceType getCategoria() { return categoria; }
    public void setCategoria(com.model.enums.DeviceType categoria) { this.categoria = categoria; }
}

