package com.model;

import com.model.entities.*;
import com.model.enums.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataStore {
    private final List<com.model.entities.Profesor> profesores = new ArrayList<>();
    private final List<com.model.entities.Item> items = new ArrayList<>();
    private final List<com.model.entities.Prestamo> prestamos = new ArrayList<>();
    private final List<com.model.entities.Incidencia> incidencias = new ArrayList<>();

    // Nuevas colecciones
    private final List<com.model.entities.Persona> personas = new ArrayList<>();
    private final List<com.model.entities.Device> dispositivos = new ArrayList<>();
    private final List<com.model.entities.Modelo> modelos = new ArrayList<>();
    private final List<com.model.entities.Averia> averias = new ArrayList<>();
    private final List<com.model.entities.ArmarioCarga> armarios = new ArrayList<>();
    private final List<com.model.entities.PersonaDispositivo> asignaciones = new ArrayList<>();

    private final ObjectMapper mapper;
    private final Path dataDir;

    public DataStore() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        dataDir = Path.of("data");
        try { if (!Files.exists(dataDir)) Files.createDirectories(dataDir); } catch (Exception e) { /* ignore */ }

        loadAll();

        // Datos iniciales de ejemplo si no hay datos cargados
        if (personas.isEmpty() && dispositivos.isEmpty() && modelos.isEmpty()) {
            profesores.add(new com.model.entities.Profesor("Ana Pérez","ana@uni.edu"));
            profesores.add(new com.model.entities.Profesor("Luis Gómez","luis@uni.edu"));

            items.add(new com.model.entities.Item("Taladro", 5, "A1"));
            items.add(new com.model.entities.Item("Martillo", 12, "A2"));
            items.add(new com.model.entities.Item("Cinta métrica", 20, "B1"));

            com.model.entities.Incidencia inc = new com.model.entities.Incidencia("Puerta de almacén atascada");
            incidencias.add(inc);

            // ejemplo persona
            com.model.entities.Persona p = new com.model.entities.Persona("María","Lopez","maria@uni.edu", com.model.enums.Role.PROFESOR);
            personas.add(p);

            // ejemplo modelo y dispositivo
            com.model.entities.Modelo m = new com.model.entities.Modelo();
            m.setFabricante("HP");
            m.setNombre("LaserJet");
            m.setCategoria(com.model.enums.DeviceType.IMPRESORA);
            modelos.add(m);

            com.model.entities.Impresora imp = new com.model.entities.Impresora();
            imp.setSerial("IMP-001");
            imp.setModeloId(m.getId());
            imp.setIp("192.168.1.50");
            dispositivos.add(imp);

            // ejemplo averia
            com.model.entities.Averia a = new com.model.entities.Averia();
            a.setDescripcion("Atasco papel impresora");
            a.setDispositivoId(imp.getId());
            a.setPersonaReportaId(p.getId());
            averias.add(a);

            // ejemplo armario
            com.model.entities.ArmarioCarga ac = new com.model.entities.ArmarioCarga();
            ac.setId("AC-1");
            ac.setUbicacion("Planta baja");
            ac.setCapacidad(50);
            ac.setOcupados(12);
            armarios.add(ac);

            // ejemplo asignacion
            com.model.entities.PersonaDispositivo pd = new com.model.entities.PersonaDispositivo();
            pd.setPersonaId(p.getId());
            pd.setDispositivoId(imp.getId());
            asignaciones.add(pd);

            saveAll();
        }
    }

    public List<com.model.entities.Profesor> getProfesores() {
        return profesores;
    }

    public List<com.model.entities.Item> getItems() {
        return items;
    }

    public List<com.model.entities.Prestamo> getPrestamos() {
        return prestamos;
    }

    public List<com.model.entities.Incidencia> getIncidencias() {
        return incidencias;
    }

    public void addProfesor(com.model.entities.Profesor p) {
        profesores.add(p);
        saveAll();
    }

    public void addPrestamo(com.model.entities.Prestamo p) {
        prestamos.add(p);
        saveAll();
    }

    public void addIncidencia(com.model.entities.Incidencia i) {
        incidencias.add(i);
        saveAll();
    }

    public List<com.model.entities.Incidencia> filtrarIncidenciasPorEstado(com.model.entities.Incidencia.Estado estado) {
        return incidencias.stream().filter(i -> i.getEstado() == estado).collect(Collectors.toList());
    }

    // Métodos para nuevas entidades
    public List<com.model.entities.Persona> getPersonas() { return personas; }
    public Optional<com.model.entities.Persona> findPersonaById(String id) { return personas.stream().filter(p -> p.getId().equals(id)).findFirst(); }
    public void addPersona(com.model.entities.Persona p) { personas.add(p); saveAll(); }

    public List<com.model.entities.Device> getDispositivos() { return dispositivos; }
    public Optional<com.model.entities.Device> findDeviceById(String id) { return dispositivos.stream().filter(d -> d.getId().equals(id)).findFirst(); }
    public void addDevice(com.model.entities.Device d) { dispositivos.add(d); saveAll(); }

    public List<com.model.entities.Modelo> getModelos() { return modelos; }
    public Optional<com.model.entities.Modelo> findModeloById(String id) { return modelos.stream().filter(m -> m.getId().equals(id)).findFirst(); }
    public void addModelo(com.model.entities.Modelo m) { modelos.add(m); saveAll(); }

    public List<com.model.entities.Averia> getAverias() { return averias; }
    public Optional<com.model.entities.Averia> findAveriaById(String id) { return averias.stream().filter(a -> a.getId().equals(id)).findFirst(); }
    public void addAveria(com.model.entities.Averia a) { averias.add(a); saveAll(); }

    public List<com.model.entities.Averia> filtrarAveriasPorEstado(com.model.entities.Averia.Estado estado) {
        return averias.stream().filter(a -> a.getEstado() == estado).collect(Collectors.toList());
    }

    public List<com.model.entities.ArmarioCarga> getArmarios() { return armarios; }
    public void addArmario(com.model.entities.ArmarioCarga ac) { armarios.add(ac); saveAll(); }

    public List<com.model.entities.PersonaDispositivo> getAsignaciones() { return asignaciones; }
    public void addAsignacion(com.model.entities.PersonaDispositivo pd) { asignaciones.add(pd); saveAll(); }

    // Persistencia JSON (implementada con Jackson)
    public void saveAll() {
        try {
            mapper.writeValue(dataDir.resolve("personas.json").toFile(), personas);
            mapper.writeValue(dataDir.resolve("dispositivos.json").toFile(), dispositivos);
            mapper.writeValue(dataDir.resolve("modelos.json").toFile(), modelos);
            mapper.writeValue(dataDir.resolve("averias.json").toFile(), averias);
            mapper.writeValue(dataDir.resolve("armarios.json").toFile(), armarios);
            mapper.writeValue(dataDir.resolve("asignaciones.json").toFile(), asignaciones);
            mapper.writeValue(dataDir.resolve("items.json").toFile(), items);
            mapper.writeValue(dataDir.resolve("prestamos.json").toFile(), prestamos);
            mapper.writeValue(dataDir.resolve("profesores.json").toFile(), profesores);
            mapper.writeValue(dataDir.resolve("incidencias.json").toFile(), incidencias);
        } catch (Exception e) {
            // No lanzar excepción en el hilo UI; registrar en consola
            System.err.println("Error guardando datos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadAll() {
        try {
            Path p;
            p = dataDir.resolve("personas.json"); if (Files.exists(p)) { var arr = mapper.readValue(p.toFile(), com.model.entities.Persona[].class); for (var v : arr) personas.add(v); }
            p = dataDir.resolve("dispositivos.json"); if (Files.exists(p)) { var arr = mapper.readValue(p.toFile(), com.model.entities.Device[].class); for (var v : arr) dispositivos.add(v); }
            p = dataDir.resolve("modelos.json"); if (Files.exists(p)) { var arr = mapper.readValue(p.toFile(), com.model.entities.Modelo[].class); for (var v : arr) modelos.add(v); }
            p = dataDir.resolve("averias.json"); if (Files.exists(p)) { var arr = mapper.readValue(p.toFile(), com.model.entities.Averia[].class); for (var v : arr) averias.add(v); }
            p = dataDir.resolve("armarios.json"); if (Files.exists(p)) { var arr = mapper.readValue(p.toFile(), com.model.entities.ArmarioCarga[].class); for (var v : arr) armarios.add(v); }
            p = dataDir.resolve("asignaciones.json"); if (Files.exists(p)) { var arr = mapper.readValue(p.toFile(), com.model.entities.PersonaDispositivo[].class); for (var v : arr) asignaciones.add(v); }
            p = dataDir.resolve("items.json"); if (Files.exists(p)) { var arr = mapper.readValue(p.toFile(), com.model.entities.Item[].class); for (var v : arr) items.add(v); }
            p = dataDir.resolve("prestamos.json"); if (Files.exists(p)) { var arr = mapper.readValue(p.toFile(), com.model.entities.Prestamo[].class); for (var v : arr) prestamos.add(v); }
            p = dataDir.resolve("profesores.json"); if (Files.exists(p)) { var arr = mapper.readValue(p.toFile(), com.model.entities.Profesor[].class); for (var v : arr) profesores.add(v); }
            p = dataDir.resolve("incidencias.json"); if (Files.exists(p)) { var arr = mapper.readValue(p.toFile(), com.model.entities.Incidencia[].class); for (var v : arr) incidencias.add(v); }
        } catch (Exception e) {
            System.err.println("Error cargando datos: " + e.getMessage());
        }
    }
}
