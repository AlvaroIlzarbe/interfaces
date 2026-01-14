package com.model.entities;

public class Profesor {
    private static int COUNTER = 1;
    private final int id;
    private String nombre;
    private String email;

    public Profesor(String nombre, String email) {
        this.id = COUNTER++;
        this.nombre = nombre;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() { return nombre + " (" + email + ")"; }
}

