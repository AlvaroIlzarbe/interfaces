package com.model.entities;

import com.model.enums.Role;

import java.time.LocalDate;
import java.util.UUID;

public class Persona {
    private final String id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Role role;
    private String departamento;
    private LocalDate fechaAlta;
    private String notas;

    public Persona() {
        this.id = UUID.randomUUID().toString();
        this.fechaAlta = LocalDate.now();
    }

    public Persona(String nombre, String apellido, String email, Role role) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.role = role;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }

    @Override
    public String toString() { return (nombre != null ? nombre : "") + (apellido != null ? " " + apellido : "") + " (" + (role != null ? role : "") + ")"; }
}

