package com.ui;

import com.service.impl.ServiceRegistry;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final ServiceRegistry services;

    public MainFrame(ServiceRegistry services) {
        this.services = services;
        initUI();
    }

    private void initUI() {
        setTitle("Gestión de Almacén");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Color palette (azul eléctrico)
        Color primary = new Color(0x00_7B_FF);
        Color bg = new Color(0xF6, 0xF8, 0xFA);
        getContentPane().setBackground(bg);

        // Layout
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(primary);
        header.setPreferredSize(new Dimension(100, 60));
        JLabel title = new JLabel("  Gestión de Almacén");
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));
        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setBackground(Color.WHITE);
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JButton btnDashboard = new JButton("Dashboard");
        JButton btnPrestamos = new JButton("Préstamos");
        JButton btnIncidencias = new JButton("Incidencias");
        JButton btnPersonas = new JButton("Personas");
        JButton btnInformes = new JButton("Informes");

        btnDashboard.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnPrestamos.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnIncidencias.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnPersonas.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnInformes.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(btnDashboard);
        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(btnPrestamos);
        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(btnIncidencias);
        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(btnPersonas);
        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(btnInformes);

        add(sidebar, BorderLayout.WEST);

        // Main content area (card layout)
        CardLayout cards = new CardLayout();
        JPanel content = new JPanel(cards);

        DashboardPanel dash = new DashboardPanel(services);
        LoansPanel loans = new LoansPanel(services);
        IncidentsPanel incidents = new IncidentsPanel(services);
        PersonsPanel persons = new PersonsPanel(services);
        ReportsPanel reports = new ReportsPanel(services);

        content.add(dash, "DASH");
        content.add(loans, "LOANS");
        content.add(incidents, "INC");
        content.add(persons, "PROF");
        content.add(reports, "REP");

        add(content, BorderLayout.CENTER);

        // Button actions
        btnDashboard.addActionListener(e -> cards.show(content, "DASH"));
        btnPrestamos.addActionListener(e -> cards.show(content, "LOANS"));
        btnIncidencias.addActionListener(e -> cards.show(content, "INC"));
        btnPersonas.addActionListener(e -> cards.show(content, "PROF"));
        btnInformes.addActionListener(e -> cards.show(content, "REP"));
    }
}
