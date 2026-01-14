package com.model.entities;

import com.model.enums.DeviceType;

public class PC extends Device {
    private String cpu;
    private int ramMb;
    private String os;
    private String mac;

    public PC() { super(); this.tipo = DeviceType.PC; }

    public String getCpu() { return cpu; }
    public void setCpu(String cpu) { this.cpu = cpu; }
    public int getRamMb() { return ramMb; }
    public void setRamMb(int ramMb) { this.ramMb = ramMb; }
    public String getOs() { return os; }
    public void setOs(String os) { this.os = os; }
    public String getMac() { return mac; }
    public void setMac(String mac) { this.mac = mac; }
}

