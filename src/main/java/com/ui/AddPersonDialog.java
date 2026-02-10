package com.ui;

import com.model.enums.Role;

import javax.swing.*;
import java.awt.*;

public class AddPersonDialog extends JDialog {
    private boolean saved = false;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JTextField txtDepartamento;
    private JComboBox<Role> cbRole;

    public AddPersonDialog(Window owner) {
        super(owner, "Añadir persona", ModalityType.APPLICATION_MODAL);
        setSize(420,300);

        JPanel main = new JPanel(new BorderLayout());
        main.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel form = new JPanel(new GridLayout(6,2,6,6));
        form.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        form.add(txtNombre);
        form.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        form.add(txtApellido);
        form.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        form.add(txtEmail);
        form.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        form.add(txtTelefono);
        form.add(new JLabel("Departamento:"));
        txtDepartamento = new JTextField();
        form.add(txtDepartamento);
        form.add(new JLabel("Rol:"));
        cbRole = new JComboBox<>(Role.values());
        form.add(cbRole);

        main.add(form, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnGuardar = new JButton("Guardar");
        bottom.add(btnCancelar);
        bottom.add(btnGuardar);
        main.add(bottom, BorderLayout.SOUTH);

        btnCancelar.addActionListener(e -> dispose());
        btnGuardar.addActionListener(e -> {
            if (txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre obligatorio.");
                return;
            }
            saved = true;
            dispose();
        });

        setContentPane(main);
    }

    public boolean isSaved() { return saved; }
    public String getNombre() { return txtNombre.getText().trim(); }
    public String getApellido() { return txtApellido.getText().trim(); }
    public String getEmail() { return txtEmail.getText().trim(); }
    public String getTelefono() { return txtTelefono.getText().trim(); }
    public String getDepartamento() { return txtDepartamento.getText().trim(); }
    public Role getRole() { return (Role) cbRole.getSelectedItem(); }
}
