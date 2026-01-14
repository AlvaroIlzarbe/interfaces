package com.ui;

import com.model.entities.Prestamo;
import com.service.impl.ServiceRegistry;
import com.service.LoanService;

import javax.swing.*;
import java.awt.*;

public class LoansPanel extends JPanel {
    private final ServiceRegistry services;
    private final LoanService loanService;
    private final DefaultListModel<Prestamo> model = new DefaultListModel<>();

    public LoansPanel(ServiceRegistry services) {
        this.services = services;
        this.loanService = services.getLoanService();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNuevo = new JButton("Nuevo préstamo");
        top.add(btnNuevo);
        add(top, BorderLayout.NORTH);

        JList<Prestamo> list = new JList<>(model);
        list.setVisibleRowCount(10);
        add(new JScrollPane(list), BorderLayout.CENTER);

        refreshList();

        btnNuevo.addActionListener(e -> {
            LoanDialog dlg = new LoanDialog(SwingUtilities.getWindowAncestor(this), services.getStore());
            dlg.setLocationRelativeTo(this);
            dlg.setVisible(true);
            if (dlg.isSaved()) {
                refreshList();
                JOptionPane.showMessageDialog(this, "Préstamo registrado correctamente.");
            }
        });
    }

    private void refreshList() {
        model.clear();
        for (Prestamo p : loanService.listAll()) model.addElement(p);
    }
}
