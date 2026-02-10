Gestión de Almacén - Instrucciones rápidas

Resumen
- Aplicación Java Swing para gestionar dispositivos, personas, préstamos y averías.
- Estilo: tonos azules eléctricos, texto negro; interfaz pensada para ser cómoda e intuitiva.

Cómo ejecutar
- Con Maven (instalar Maven si no está disponible en tu sistema):

```powershell
mvn -DskipTests=true exec:java
```

- Alternativa: ejecutar la clase `com.Main` desde tu IDE.

Funcionalidades añadidas (rápidas)
- Personas con roles (PROFESOR, ADMIN, ALUMNO). Diálogo: `AddPersonDialog` (antes AddProfessorDialog).
- Panel de gestión de personas: `PersonsPanel` (antes ProfessorsPanel).
- Entidades: Device, Modelo, Averia, ArmarioCarga, PersonaDispositivo, Prestamo.
- Reports: `ReportsPanel` permite filtrar por Persona/Fechas y exportar CSV con cabecera.
- Incidencias/Averías: `IncidentsPanel` (crear y cerrar averías con solución y fecha de cierre).
- Persistencia local JSON en carpeta `data/` (creada automáticamente).

Cómo crear un informe filtrado (C)
1. Ir a Informes (panel "Informes").
2. Seleccionar tipo (Préstamo/Avería), elegir Persona (combo editable) y las fechas Desde/Hasta (selectores de fecha).
3. Pulsar "Generar informe" para ver los resultados.
4. Pulsar "Exportar CSV" para guardar un CSV temporal (se muestra la ruta).

Cómo añadir una persona (A)
1. Ir a Personas (panel "Personas").
2. Pulsar "Añadir persona".
3. Rellenar nombre, apellido, email, teléfono, departamento y rol.
4. Pulsar Guardar. La persona se persiste en `data/personas.json`.

Cómo cerrar una avería (E)
1. Ir a Incidencias (panel "Incidencias").
2. Seleccionar una avería en la lista.
3. Pulsar "Cerrar avería" y escribir la solución aplicada. La avería se cierra y se guarda en `data/averias.json`.

Ubicación de archivos importantes
- Código UI: `src/main/java/com/ui/` (fíjate en `AddPersonDialog.java`, `PersonsPanel.java`, `IncidentsPanel.java`, `ReportsPanel.java`).
- Modelos y DataStore: `src/main/java/com/model/` (`DataStore.java` implementa persistencia JSON en `data/`).

Siguientes pasos recomendados
- Pulir UI: añadir iconos, ajustar tamaños, mejorar layout de tablas.
- Añadir tests automáticos (JUnit 5) para CRUD y persistencia.

(Archivo actualizado automáticamente por el asistente)
