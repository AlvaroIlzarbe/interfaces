package com.model.entities;

import com.model.enums.DeviceType;

public class Portatil extends Device {
    private double pesoKg;
    private int bateriaPct;

    public Portatil() { super(); this.tipo = DeviceType.PORTATIL; }

    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) { this.pesoKg = pesoKg; }
    public int getBateriaPct() { return bateriaPct; }
    public void setBateriaPct(int bateriaPct) { this.bateriaPct = bateriaPct; }
}

