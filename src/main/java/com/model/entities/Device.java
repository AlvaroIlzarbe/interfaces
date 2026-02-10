package com.model.entities;

import com.model.enums.DeviceStatus;
import com.model.enums.DeviceType;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Device {
    protected String id;
    protected String serial;
    protected String modeloId;
    protected DeviceType tipo;
    protected String ubicacion;
    protected DeviceStatus estado;
    protected LocalDate fechaAlta;
    protected String notas;

    public Device() {
        this.id = UUID.randomUUID().toString();
        this.fechaAlta = LocalDate.now();
        this.estado = DeviceStatus.OPERATIVO;
    }

    public String getId() { return id; }
    public String getSerial() { return serial; }
    public void setSerial(String serial) { this.serial = serial; }
    public String getModeloId() { return modeloId; }
    public void setModeloId(String modeloId) { this.modeloId = modeloId; }
    public DeviceType getTipo() { return tipo; }
    public void setTipo(DeviceType tipo) { this.tipo = tipo; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public DeviceStatus getEstado() { return estado; }
    public void setEstado(DeviceStatus estado) { this.estado = estado; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }

    @Override
    public String toString() { return (serial != null ? serial : id) + " - " + (tipo != null ? tipo : ""); }
}

