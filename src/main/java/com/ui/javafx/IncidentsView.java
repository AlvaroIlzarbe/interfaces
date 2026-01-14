package com.ui.javafx;

import com.model.entities.Averia;
import com.service.impl.ServiceRegistry;
import com.service.IncidentService;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class IncidentsView extends VBox {
    private final ServiceRegistry services;
    private final IncidentService incidentService;

    public IncidentsView(ServiceRegistry services, Runnable onBack) {
        this.services = services;
        this.incidentService = services.getIncidentService();
        build(onBack);
    }

    private void build(Runnable onBack) {
        setPadding(new Insets(10));
        setSpacing(8);
        getStyleClass().add("content-panel");

        Label title = new Label("Gestión de Incidencias");
        title.setStyle("-fx-font-size:16px; -fx-font-weight:bold;");

        ListView<Averia> list = new ListView<>();
        list.setItems(FXCollections.observableArrayList(incidentService.listAll()));

        Button btnNueva = new Button("Nueva avería");
        btnNueva.setOnAction(e -> {
            TextInputDialog dlg = new TextInputDialog();
            dlg.setTitle("Nueva avería");
            dlg.setHeaderText("Descripción");
            dlg.showAndWait().ifPresent(desc -> {
                if (!desc.trim().isEmpty()) {
                    Averia a = new Averia();
                    a.setDescripcion(desc.trim());
                    incidentService.create(a);
                    list.getItems().add(a);
                }
            });
        });

        Button btnCerrar = new Button("Cerrar avería");
        btnCerrar.setOnAction(e -> {
            Averia sel = list.getSelectionModel().getSelectedItem();
            if (sel == null) { UiUtils.alertError("Selecciona una avería para cerrar."); return; }
            TextInputDialog dlg = new TextInputDialog();
            dlg.setTitle("Cerrar avería");
            dlg.setHeaderText("Describe la solución");
            dlg.showAndWait().ifPresent(sol -> {
                incidentService.close(sel, sol.trim());
                list.getItems().set(list.getItems().indexOf(sel), sel);
                UiUtils.alertInfo("Avería cerrada.");
            });
        });

        ChoiceBox<String> cbFilter = new ChoiceBox<>(FXCollections.observableArrayList("Todas","ABIERTO","EN_PROGRESO","CERRADO"));
        cbFilter.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV == null || newV.equals("Todas")) list.setItems(FXCollections.observableArrayList(incidentService.listAll()));
            else {
                Averia.Estado est = Averia.Estado.valueOf(newV);
                list.setItems(FXCollections.observableArrayList(services.getStore().filtrarAveriasPorEstado(est)));
            }
        });

        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> { if (onBack != null) onBack.run(); });

        getChildren().addAll(title, cbFilter, list, new HBoxWithSpacing(btnNueva, btnCerrar, btnVolver));
    }

    // pequeña clase interna para agrupar botones con espacio
    private static class HBoxWithSpacing extends javafx.scene.layout.HBox {
        HBoxWithSpacing(javafx.scene.Node... nodes) {
            super(8);
            setPadding(new Insets(6,0,0,0));
            getChildren().addAll(nodes);
        }
    }
}
