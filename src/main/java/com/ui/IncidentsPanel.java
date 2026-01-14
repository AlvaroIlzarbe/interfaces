package com.ui;

import com.model.entities.Averia;
import com.service.IncidentService;
import com.service.impl.ServiceRegistry;

import javax.swing.*;
import java.awt.*;

public class IncidentsPanel extends JPanel {
    private final ServiceRegistry services;
    private final IncidentService incidentService;
    private final DefaultListModel<Averia> model = new DefaultListModel<>();

    public IncidentsPanel(ServiceRegistry services) {
        this.services = services;
        this.incidentService = services.getIncidentService();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNueva = new JButton("Nueva avería");
        JButton btnCerrar = new JButton("Cerrar avería");
        JComboBox<String> cbFilter = new JComboBox<>(new String[]{"Todas","ABIERTO","EN_PROGRESO","CERRADO"});
        top.add(btnNueva);
        top.add(btnCerrar);
        top.add(cbFilter);
        add(top, BorderLayout.NORTH);

        JList<Averia> list = new JList<>(model);
        add(new JScrollPane(list), BorderLayout.CENTER);

        refreshList();

        btnNueva.addActionListener(e -> {
            String desc = JOptionPane.showInputDialog(this, "Descripción de la avería:");
            if (desc != null && !desc.trim().isEmpty()) {
                Averia a = new Averia();
                a.setDescripcion(desc.trim());
                incidentService.create(a);
                refreshList();
            }
        });

        btnCerrar.addActionListener(e -> {
            Averia sel = list.getSelectedValue();
            if (sel == null) {
                JOptionPane.showMessageDialog(this, "Selecciona una avería para cerrar.");
                return;
            }
            String solucion = JOptionPane.showInputDialog(this, "Describa la solución aplicada:");
            if (solucion != null) incidentService.close(sel, solucion.trim());
            refreshList();
            JOptionPane.showMessageDialog(this, "Avería cerrada.");
        });

        cbFilter.addActionListener(e -> {
            String val = (String) cbFilter.getSelectedItem();
            if (val != null && val.equals("Todas")) refreshList();
            else {
                Averia.Estado est = Averia.Estado.valueOf(val);
                model.clear();
                for (Averia a : services.getStore().filtrarAveriasPorEstado(est)) model.addElement(a);
            }
        });

    }

    private void refreshList() {
        model.clear();
        for (Averia a : incidentService.listAll()) model.addElement(a);
    }
}
