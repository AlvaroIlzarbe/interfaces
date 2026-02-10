package com.ui;

import com.model.entities.Persona;
import com.service.impl.ServiceRegistry;
import com.service.PersonaService;

import javax.swing.*;
import java.awt.*;

public class PersonsPanel extends JPanel {
    private final ServiceRegistry services;
    private final PersonaService personaService;
    private final DefaultListModel<Persona> model = new DefaultListModel<>();

    public PersonsPanel(ServiceRegistry services) {
        this.services = services;
        this.personaService = services.getPersonaService();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNuevo = new JButton("Añadir persona");
        top.add(btnNuevo);
        add(top, BorderLayout.NORTH);

        JList<Persona> list = new JList<>(model);
        add(new JScrollPane(list), BorderLayout.CENTER);

        refreshList();

        btnNuevo.addActionListener(e -> {
            AddPersonDialog dlg = new AddPersonDialog(SwingUtilities.getWindowAncestor(this));
            dlg.setLocationRelativeTo(this);
            dlg.setVisible(true);
            if (dlg.isSaved()) {
                Persona p = new Persona(dlg.getNombre(), dlg.getApellido(), dlg.getEmail(), dlg.getRole());
                p.setTelefono(dlg.getTelefono());
                p.setDepartamento(dlg.getDepartamento());
                personaService.create(p);
                refreshList();
                JOptionPane.showMessageDialog(this, "Persona añadida correctamente.");
            }
        });
    }

    private void refreshList() {
        model.clear();
        for (Persona p : personaService.listAll()) model.addElement(p);
    }
}
