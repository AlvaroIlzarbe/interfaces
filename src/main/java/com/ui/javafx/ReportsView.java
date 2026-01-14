package com.ui.javafx;

import com.service.impl.ServiceRegistry;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class ReportsView extends VBox {
    public ReportsView(ServiceRegistry services, Runnable onBack) {
        setPadding(new Insets(10));
        setSpacing(8);
        getStyleClass().add("content-panel");

        Label title = new Label("Informes");
        title.setStyle("-fx-font-size:16px; -fx-font-weight:bold;");
        getChildren().add(title);

        TextField txtPersona = new TextField();
        txtPersona.setPromptText("Nombre o email (opcional)");

        DatePicker dpDesde = new DatePicker();
        DatePicker dpHasta = new DatePicker();

        ChoiceBox<String> cbTipo = new ChoiceBox<>(FXCollections.observableArrayList("Préstamo","Avería"));
        cbTipo.getSelectionModel().selectFirst();

        Button btnGenerar = new Button("Generar informe");
        ListView<String> list = new ListView<>();

        btnGenerar.setOnAction(e -> {
            list.getItems().clear();
            String persona = txtPersona.getText().trim();
            LocalDate desde = dpDesde.getValue();
            LocalDate hasta = dpHasta.getValue();
            String tipo = cbTipo.getValue();

            if (tipo.equals("Préstamo")) {
                // Filtrado simple a partir del store
                var prestamos = services.getStore().getPrestamos();
                for (var p : prestamos) {
                    boolean matches = persona.isEmpty() || p.getSolicitante().contains(persona);
                    if (matches) list.getItems().add(p.toString());
                }
            } else {
                var averias = services.getStore().getAverias();
                for (var a : averias) {
                    boolean matches = persona.isEmpty() || (a.getPersonaReportaId() != null && a.getPersonaReportaId().contains(persona));
                    if (matches) list.getItems().add(a.toString());
                }
            }
        });

        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> { if (onBack != null) onBack.run(); });

        getChildren().addAll(new Label("Persona/solicitante:"), txtPersona,
                new Label("Desde:"), dpDesde,
                new Label("Hasta:"), dpHasta,
                new Label("Tipo de informe:"), cbTipo,
                btnGenerar, list, btnVolver);
    }
}
