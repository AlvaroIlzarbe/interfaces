package com.ui;

import com.service.impl.ServiceRegistry;
import com.ui.javafx.IconGenerator;
import com.ui.javafx.MainView;
import com.ui.javafx.LoginDialog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxMain extends Application {
    private ServiceRegistry registry;

    @Override
    public void init() {
        // Generar iconos placeholder si no existen
        try { IconGenerator.ensureIconsExist(); } catch (Exception ex) { System.err.println("Icon generation failed: " + ex.getMessage()); }
        // Initialize services before the UI thread starts
        registry = new ServiceRegistry();
    }

    @Override
    public void start(Stage primaryStage) {
        // Show login dialog
        LoginDialog login = new LoginDialog();
        boolean ok = login.showAndWait();
        if (!ok) {
            System.out.println("Autenticación fallida. Saliendo.");
            System.exit(0);
            return;
        }

        primaryStage.setTitle("Gestión de Almacén");

        MainView mainView = new MainView(registry);

        Scene scene = new Scene(mainView, 1000, 700);
        // Cargar CSS
        try {
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        } catch (Exception ex) {
            System.err.println("No se pudo cargar styles.css: " + ex.getMessage());
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public ServiceRegistry getRegistry() { return registry; }

    public static void main(String[] args) {
        launch(args);
    }
}
