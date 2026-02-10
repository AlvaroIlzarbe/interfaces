package com.ui.javafx;

import com.service.impl.ServiceRegistry;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashboardView extends VBox {
    public DashboardView(ServiceRegistry services, Runnable onBack) {
        setPadding(new Insets(10));
        getStyleClass().add("content-panel");
        Label title = new Label("Dashboard");
        title.setStyle("-fx-font-size:16px; -fx-font-weight:bold;");
        getChildren().add(title);

        Label info = new Label("Resumen rápido: inventario, préstamos e incidencias.");
        getChildren().add(info);

        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> { if (onBack != null) onBack.run(); });
        getChildren().add(btnVolver);
    }
}
