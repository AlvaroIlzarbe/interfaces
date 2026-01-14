package com.ui;

import com.model.DataStore;

import javax.swing.*;
import java.awt.*;

@Deprecated
public class ProfessorsPanel extends JPanel {
    public ProfessorsPanel(DataStore store) {
        setLayout(new BorderLayout());
        // Delegar en la nueva implementación PersonsPanel usando el ServiceRegistry que acepta DataStore
        com.service.impl.ServiceRegistry registry = new com.service.impl.ServiceRegistry(store);
        PersonsPanel persons = new PersonsPanel(registry);
        add(persons, BorderLayout.CENTER);
    }
}
