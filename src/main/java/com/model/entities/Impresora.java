package com.model.entities;

import com.model.enums.DeviceType;

public class Impresora extends Device {
    private String ip;
    private int bandejaCapacidad;
    private boolean color;

    public Impresora() {
        super();
        this.tipo = DeviceType.IMPRESORA;
    }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }
    public int getBandejaCapacidad() { return bandejaCapacidad; }
    public void setBandejaCapacidad(int bandejaCapacidad) { this.bandejaCapacidad = bandejaCapacidad; }
    public boolean isColor() { return color; }
    public void setColor(boolean color) { this.color = color; }
}

