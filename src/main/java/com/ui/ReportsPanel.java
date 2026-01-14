package com.ui;

import com.model.entities.Averia;
import com.model.entities.Prestamo;
import com.service.ReportService;
import com.service.impl.ServiceRegistry;
import com.service.PersonaService;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportsPanel extends JPanel {
    private final ServiceRegistry services;
    private final ReportService reportService;
    private final PersonaService personaService;
    private final DefaultListModel<String> model = new DefaultListModel<>();

    public ReportsPanel(ServiceRegistry services) {
        this.services = services;
        this.reportService = services.getReportService();
        this.personaService = services.getPersonaService();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> cbPersona = new JComboBox<>();
        cbPersona.setEditable(true);
        populatePersonas(cbPersona);

        SpinnerDateModel smDesde = new SpinnerDateModel();
        JSpinner spDesde = new JSpinner(smDesde);
        spDesde.setEditor(new JSpinner.DateEditor(spDesde, "yyyy-MM-dd"));

        SpinnerDateModel smHasta = new SpinnerDateModel();
        JSpinner spHasta = new JSpinner(smHasta);
        spHasta.setEditor(new JSpinner.DateEditor(spHasta, "yyyy-MM-dd"));

        JRadioButton rbPrestamo = new JRadioButton("Préstamo");
        JRadioButton rbAveria = new JRadioButton("Avería");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbPrestamo); bg.add(rbAveria);
        rbPrestamo.setSelected(true);

        JButton btnGenerar = new JButton("Generar informe");
        JButton btnExport = new JButton("Exportar CSV");

        top.add(new JLabel("Persona/solicitante:"));
        top.add(cbPersona);
        top.add(new JLabel("Desde:"));
        top.add(spDesde);
        top.add(new JLabel("Hasta:"));
        top.add(spHasta);
        top.add(rbPrestamo);
        top.add(rbAveria);
        top.add(btnGenerar);
        top.add(btnExport);
        add(top, BorderLayout.NORTH);

        JList<String> list = new JList<>(model);
        add(new JScrollPane(list), BorderLayout.CENTER);

        btnGenerar.addActionListener(e -> {
            model.clear();
            String persona = ((String)cbPersona.getEditor().getItem()).trim();
            LocalDate desde = dateToLocalDate((Date) spDesde.getValue());
            LocalDate hasta = dateToLocalDate((Date) spHasta.getValue());

            if (rbPrestamo.isSelected()) {
                List<Prestamo> filtered = reportService.filterPrestamos(persona, desde, hasta);
                for (Prestamo p : filtered) model.addElement(p.toString());
                if (filtered.isEmpty()) JOptionPane.showMessageDialog(this, "No hay préstamos que coincidan con el filtro.");
            } else {
                List<Averia> filtered = reportService.filterAverias(persona, desde, hasta);
                for (Averia a : filtered) model.addElement(a.toString());
                if (filtered.isEmpty()) JOptionPane.showMessageDialog(this, "No hay averías que coincidan con el filtro.");
            }
        });

        btnExport.addActionListener(e -> {
            if (model.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay datos para exportar. Genera primero el informe.");
                return;
            }
            try {
                java.io.File f = java.io.File.createTempFile("informe_", ".csv");
                try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
                    // cabecera simple
                    pw.println("Descripción");
                    for (int i = 0; i < model.size(); i++) {
                        pw.println(escapeCsv(model.get(i)));
                    }
                }
                JOptionPane.showMessageDialog(this, "Informe exportado a: " + f.getAbsolutePath());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al exportar: " + ex.getMessage());
            }
        });
    }

    private void populatePersonas(JComboBox<String> cb) {
        cb.removeAllItems();
        List<String> names = new ArrayList<>();
        for (var p : personaService.listAll()) names.add((p.getNombre() + " " + p.getApellido()).trim());
        for (String n : names) cb.addItem(n);
    }

    private LocalDate dateToLocalDate(Date d) {
        if (d == null) return null;
        Instant instant = d.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private String escapeCsv(String s) { return '"' + s.replace("\"", "\"\"") + '"'; }
}
