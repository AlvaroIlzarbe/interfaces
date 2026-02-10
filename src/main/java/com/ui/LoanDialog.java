package com.ui;

import com.model.DataStore;
import com.model.entities.Item;
import com.model.entities.Prestamo;
import com.service.LoanService;
import com.service.impl.ServiceRegistry;

import javax.swing.*;
import java.awt.*;

public class LoanDialog extends JDialog {
    private boolean saved = false;

    public LoanDialog(Window owner, ServiceRegistry services) {
        super(owner, "Alta de Préstamo", ModalityType.APPLICATION_MODAL);
        setSize(600, 400);

        LoanService loanService = services.getLoanService();

        JPanel main = new JPanel(new BorderLayout());
        main.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.gridy = 0; c.weightx = 0;
        form.add(new JLabel("Solicitante:"), c);
        c.gridx = 1; c.weightx = 1;
        JTextField txtSolicitante = new JTextField();
        form.add(txtSolicitante, c);

        c.gridx = 0; c.gridy = 1; c.weightx = 0;
        form.add(new JLabel("Item:"), c);
        c.gridx = 1; c.weightx = 1;
        DefaultComboBoxModel<Item> itemsModel = new DefaultComboBoxModel<>();
        for (Item it : services.getStore().getItems()) itemsModel.addElement(it);
        JComboBox<Item> cmbItems = new JComboBox<>(itemsModel);
        form.add(cmbItems, c);

        c.gridx = 0; c.gridy = 2; c.weightx = 0;
        form.add(new JLabel("Cantidad:"), c);
        c.gridx = 1; c.weightx = 1;
        JTextField txtCantidad = new JTextField("1");
        form.add(txtCantidad, c);

        main.add(form, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        bottom.add(btnCancelar);
        bottom.add(btnGuardar);
        main.add(bottom, BorderLayout.SOUTH);

        btnCancelar.addActionListener(e -> dispose());
        btnGuardar.addActionListener(e -> {
            String solicitante = txtSolicitante.getText().trim();
            Item seleccionado = (Item) cmbItems.getSelectedItem();
            int cantidad = 1;
            try { cantidad = Integer.parseInt(txtCantidad.getText().trim()); } catch (NumberFormatException ex) { /* ignore */ }

            if (solicitante.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo solicitante es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un item.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cantidad <= 0 || cantidad > seleccionado.getStock()) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida o superior al stock.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Prestamo p = new Prestamo(solicitante);
            // añadir la cantidad de veces (simplificación)
            for (int i=0;i<cantidad;i++) p.addItem(seleccionado);
            seleccionado.setStock(seleccionado.getStock()-cantidad);
            loanService.create(p);
            services.getStore().saveAll();
            saved = true;
            dispose();
        });

        setContentPane(main);
    }

    // Constructor de compatibilidad que acepta DataStore
    public LoanDialog(Window owner, DataStore store) {
        this(owner, new ServiceRegistry(store));
    }

    public boolean isSaved() { return saved; }
}
