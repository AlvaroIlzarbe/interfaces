package com.model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Prestamo {
    private static int COUNTER = 1;
    private final int id;
    private String solicitante;
    private List<Item> items = new ArrayList<>();
    private LocalDateTime fecha;

    public Prestamo(String solicitante) {
        this.id = COUNTER++;
        this.solicitante = solicitante;
        this.fecha = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getSolicitante() { return solicitante; }
    public void setSolicitante(String solicitante) { this.solicitante = solicitante; }
    public List<Item> getItems() { return items; }
    public void addItem(Item item) { items.add(item); }
    public LocalDateTime getFecha() { return fecha; }

    @Override
    public String toString() { return "Prestamo #" + id + " - " + solicitante + " (" + items.size() + " items)"; }
}

