package com.ui.javafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginDialog {
    private boolean authenticated = false;

    public boolean showAndWait() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Iniciar sesión");

        VBox root = new VBox(12);
        root.setPadding(new Insets(18));
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("login-root");

        Label info = new Label("Acceso requerido. Credenciales por defecto: user: root; pass: root");
        info.setWrapText(true);
        info.getStyleClass().add("app-subtitle");

        GridPane form = new GridPane();
        form.setVgap(8);
        form.setHgap(8);
        form.setAlignment(Pos.CENTER);

        Label lblUser = new Label("Usuario:");
        TextField txtUser = new TextField();
        form.add(lblUser, 0, 0);
        form.add(txtUser, 1, 0);

        Label lblPass = new Label("Contraseña:");
        PasswordField txtPass = new PasswordField();
        form.add(lblPass, 0, 1);
        form.add(txtPass, 1, 1);

        Button btnLogin = new Button("Entrar");
        btnLogin.getStyleClass().add("login-button");
        Button btnCancel = new Button("Salir");
        btnCancel.getStyleClass().add("login-cancel");

        btnLogin.setDefaultButton(true);
        btnCancel.setCancelButton(true);

        HBox actions = new HBox(10, btnLogin, btnCancel);
        actions.setAlignment(Pos.CENTER);

        btnLogin.setOnAction(e -> {
            String u = txtUser.getText();
            String p = txtPass.getText();
            if ("root".equals(u) && "root".equals(p)) {
                authenticated = true;
                stage.close();
            } else {
                UiUtils.alertError("Usuario o contraseña incorrectos. Prueba user=root, pass=root");
            }
        });

        btnCancel.setOnAction(e -> {
            authenticated = false;
            stage.close();
        });

        txtPass.setOnKeyPressed(ev -> { if (ev.getCode() == KeyCode.ENTER) btnLogin.fire(); });
        txtUser.setOnKeyPressed(ev -> { if (ev.getCode() == KeyCode.ENTER) btnLogin.fire(); });

        root.getChildren().addAll(info, form, actions);

        Scene scene = new Scene(root, 420, 220);
        // aplicar CSS si está disponible
        try { scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm()); } catch (Exception ignored) {}

        stage.setScene(scene);
        stage.showAndWait();
        return authenticated;
    }
}
