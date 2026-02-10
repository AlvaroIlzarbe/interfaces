package com.model.entities;

public class Item {
    private static int COUNTER = 1;
    private final int id;
    private String nombre;
    private int stock;
    private String ubicacion;

    public Item(String nombre, int stock, String ubicacion) {
        this.id = COUNTER++;
        this.nombre = nombre;
        this.stock = stock;
        this.ubicacion = ubicacion;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    @Override
    public String toString() { return nombre + " [" + stock + "]"; }
}

