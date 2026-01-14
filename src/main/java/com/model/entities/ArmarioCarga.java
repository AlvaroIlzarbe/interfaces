package com.model.entities;

import com.model.enums.DeviceStatus;

public class ArmarioCarga {
    private String id;
    private String ubicacion;
    private int capacidad;
    private int ocupados;
    private String notas;
    private DeviceStatus estado;

    public ArmarioCarga() { this.estado = DeviceStatus.OPERATIVO; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public int getOcupados() { return ocupados; }
    public void setOcupados(int ocupados) { this.ocupados = ocupados; }
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
    public DeviceStatus getEstado() { return estado; }
    public void setEstado(DeviceStatus estado) { this.estado = estado; }
}

