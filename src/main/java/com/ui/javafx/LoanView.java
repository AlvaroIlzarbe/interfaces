package com.ui.javafx;

import com.model.entities.Item;
import com.service.impl.ServiceRegistry;
import com.service.LoanService;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class LoanView extends VBox {
    private final ServiceRegistry services;

    public LoanView(ServiceRegistry services, Runnable onBack) {
        this.services = services;
        build(onBack);
    }

    private void build(Runnable onBack) {
        setPadding(new Insets(10));
        setSpacing(8);
        getStyleClass().add("content-panel");

        Label title = new Label("Alta de Préstamo");
        title.setStyle("-fx-font-size:16px; -fx-font-weight:bold;");

        GridPane form = new GridPane();
        form.setVgap(6);
        form.setHgap(6);

        form.add(new Label("Solicitante:"), 0, 0);
        TextField txtSolicitante = new TextField();
        form.add(txtSolicitante, 1, 0);

        form.add(new Label("Item:"), 0, 1);
        ComboBox<Item> cbItems = new ComboBox<>();
        cbItems.getItems().addAll(services.getStore().getItems());
        form.add(cbItems, 1, 1);

        form.add(new Label("Cantidad:"), 0, 2);
        Spinner<Integer> spCantidad = new Spinner<>(1, 1000, 1);
        form.add(spCantidad, 1, 2);

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
            String solicitante = txtSolicitante.getText().trim();
            Item seleccionado = cbItems.getValue();
            int cantidad = spCantidad.getValue();
            if (solicitante.isEmpty()) { UiUtils.alertError("El campo solicitante es obligatorio."); return; }
            if (seleccionado == null) { UiUtils.alertError("Selecciona un item."); return; }
            if (cantidad <= 0 || cantidad > seleccionado.getStock()) { UiUtils.alertError("Cantidad inválida o superior al stock."); return; }
            LoanService loanService = services.getLoanService();
            var p = new com.model.entities.Prestamo(solicitante);
            for (int i=0;i<cantidad;i++) p.addItem(seleccionado);
            seleccionado.setStock(seleccionado.getStock()-cantidad);
            loanService.create(p);
            services.getStore().saveAll();
            UiUtils.alertInfo("Préstamo registrado correctamente.");
        });

        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> { if (onBack != null) onBack.run(); });

        getChildren().addAll(title, form, btnGuardar, btnVolver);
    }
}
