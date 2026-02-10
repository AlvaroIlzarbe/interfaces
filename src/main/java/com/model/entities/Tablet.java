package com.model.entities;

import com.model.enums.DeviceType;

public class Tablet extends Device {
    private double pantallaPulgadas;

    public Tablet() { super(); this.tipo = DeviceType.TABLET; }

    public double getPantallaPulgadas() { return pantallaPulgadas; }
    public void setPantallaPulgadas(double pantallaPulgadas) { this.pantallaPulgadas = pantallaPulgadas; }
}

