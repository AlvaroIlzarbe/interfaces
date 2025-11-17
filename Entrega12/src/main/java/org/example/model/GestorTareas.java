package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GestorTareas {
    private List<Tarea> tareas;

    public GestorTareas() {
        tareas = new ArrayList<>();
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public boolean eliminarTarea(Tarea tarea) {
        return tareas.remove(tarea);
    }

    public List<Tarea> buscarPorFecha(int anio, int mes, int dia) {
        LocalDate fecha = LocalDate.of(anio, mes, dia);
        return tareas.stream()
                .filter(t -> t.getFecha().equals(fecha))
                .collect(Collectors.toList());
    }

    public List<Tarea> listarTareasOrdenadasPorFecha() {
        return tareas.stream()
                .sorted(Comparator.comparing(Tarea::getFecha))
                .collect(Collectors.toList());
    }

    public boolean modificarTarea(int indice, Tarea nuevaTarea) {
        if (indice >= 0 && indice < tareas.size()) {
            tareas.set(indice, nuevaTarea);
            return true;
        }
        return false;
    }

    public List<Tarea> getTareas() {
        return new ArrayList<>(tareas);
    }
}
