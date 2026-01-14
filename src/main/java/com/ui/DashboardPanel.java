package com.ui;

import com.service.impl.ServiceRegistry;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    public DashboardPanel(ServiceRegistry services) {
        setLayout(new BorderLayout());
        setBackground(new Color(0xF6,0xF8,0xFA));
        JLabel lbl = new JLabel("Bienvenido al panel de control");
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 18f));
        lbl.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(lbl, BorderLayout.NORTH);

        JTextArea info = new JTextArea("Resumen rápido:\n- Inventario: " + services.getStore().getItems().size() + " items\n- Préstamos: " + services.getStore().getPrestamos().size() + "\n- Incidencias: " + services.getStore().getIncidencias().size());
        info.setEditable(false);
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(info, BorderLayout.CENTER);
    }
}
