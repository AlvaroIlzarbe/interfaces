package com.ui.javafx;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class UiUtils {
    public static void alertInfo(String msg) {
        Platform.runLater(() -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText(msg);
            a.showAndWait();
        });
    }

    public static void alertError(String msg) {
        Platform.runLater(() -> {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(msg);
            a.showAndWait();
        });
    }
}

