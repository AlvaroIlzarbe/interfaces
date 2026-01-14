package com.model.entities;

import com.model.enums.DeviceType;

public class PantallaTactil extends Device {
    private double tamanyoPulgadas;

    public PantallaTactil() { super(); this.tipo = DeviceType.PANTALLA_TACTIL; }

    public double getTamanyoPulgadas() { return tamanyoPulgadas; }
    public void setTamanyoPulgadas(double tamanyoPulgadas) { this.tamanyoPulgadas = tamanyoPulgadas; }
}

