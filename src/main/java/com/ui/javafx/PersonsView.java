package com.ui.javafx;

import com.model.entities.Persona;
import com.model.enums.Role;
import com.service.impl.ServiceRegistry;
import com.service.PersonaService;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PersonsView extends VBox {
    private final ServiceRegistry services;

    public PersonsView(ServiceRegistry services, Runnable onBack) {
        this.services = services;
        build(onBack);
    }

    private void build(Runnable onBack) {
        setPadding(new Insets(10));
        setSpacing(8);
        getStyleClass().add("content-panel");

        Label title = new Label("Gestión de Personas");
        title.setStyle("-fx-font-size:16px; -fx-font-weight:bold;");

        GridPane form = new GridPane();
        form.setVgap(6);
        form.setHgap(6);

        form.add(new Label("Nombre:"), 0, 0);
        TextField txtNombre = new TextField();
        form.add(txtNombre, 1, 0);

        form.add(new Label("Apellido:"), 0, 1);
        TextField txtApellido = new TextField();
        form.add(txtApellido, 1, 1);

        form.add(new Label("Email:"), 0, 2);
        TextField txtEmail = new TextField();
        form.add(txtEmail, 1, 2);

        form.add(new Label("Rol:"), 0, 3);
        ChoiceBox<Role> cbRole = new ChoiceBox<>();
        cbRole.getItems().addAll(Role.values());
        form.add(cbRole, 1, 3);

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
            if (txtNombre.getText().trim().isEmpty()) { UiUtils.alertError("Nombre obligatorio"); return; }
            Persona p = new Persona(txtNombre.getText().trim(), txtApellido.getText().trim(), txtEmail.getText().trim(), cbRole.getValue());
            PersonaService personaService = services.getPersonaService();
            personaService.create(p);
            services.getStore().saveAll();
            UiUtils.alertInfo("Persona creada.");
        });

        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> { if (onBack != null) onBack.run(); });

        getChildren().addAll(title, form, btnGuardar, btnVolver);
    }
}
