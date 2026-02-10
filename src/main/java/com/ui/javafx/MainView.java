package com.ui.javafx;

import com.service.impl.ServiceRegistry;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainView extends BorderPane {
    private final ServiceRegistry services;
    private GridPane smallBottomGrid; // small buttons docked bottom when an option is active

    public MainView(ServiceRegistry services) {
        this.services = services;
        build();
    }

    private void build() {
        setPadding(new Insets(10));

        Label header = new Label("Gestión de Almacén");
        header.getStyleClass().add("app-title");
        BorderPane.setAlignment(header, Pos.CENTER);
        setTop(header);

        // Crear la rejilla de botones pequeños (se mostrará sólo en detalle)
        smallBottomGrid = createSmallBottomGrid();

        // Mostrar home con botones grandes en el centro
        showHome();
    }

    private void showHome() {
        GridPane homeGrid = new GridPane();
        homeGrid.setHgap(18);
        homeGrid.setVgap(16);
        homeGrid.setPadding(new Insets(24));
        homeGrid.setAlignment(Pos.CENTER);

        Button b1 = createBigButton("Dashboard", "/icons/dashboard.png", "🏠");
        Button b2 = createBigButton("Préstamos", "/icons/loan.png", "📦");
        Button b3 = createBigButton("Incidencias", "/icons/incident.png", "🔧");
        Button b4 = createBigButton("Personas", "/icons/persons.png", "👥");
        Button b5 = createBigButton("Informes", "/icons/reports.png", "📊");

        homeGrid.add(b1, 0, 0);
        homeGrid.add(b2, 1, 0);
        homeGrid.add(b3, 2, 0);
        homeGrid.add(b4, 1, 1);
        homeGrid.add(b5, 2, 1);

        b1.setOnAction(e -> activateOption("dashboard"));
        b2.setOnAction(e -> activateOption("loan"));
        b3.setOnAction(e -> activateOption("incident"));
        b4.setOnAction(e -> activateOption("persons"));
        b5.setOnAction(e -> activateOption("reports"));

        setCenter(homeGrid);
        setBottom(null); // no small bottom buttons on home
    }

    private void activateOption(String key) {
        // Create top hero image for the selected option (discrete) and a view area filling the center
        StackPane hero = createHeroNode("/icons/" + key + ".png", ""); // empty labelText so no big letter appears
        hero.setPrefHeight(220);

        VBox centerBox = new VBox(8);
        centerBox.setPadding(new Insets(8));
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.getChildren().add(hero);

        StackPane viewArea = new StackPane();
        viewArea.setPrefHeight(520);
        viewArea.getStyleClass().add("content-panel");

        // load the selected view into viewArea
        switch (key) {
            case "dashboard": viewArea.getChildren().add(new DashboardView(services, this::showHome)); break;
            case "loan": viewArea.getChildren().add(new LoanView(services, this::showHome)); break;
            case "incident": viewArea.getChildren().add(new IncidentsView(services, this::showHome)); break;
            case "persons": viewArea.getChildren().add(new PersonsView(services, this::showHome)); break;
            case "reports": viewArea.getChildren().add(new ReportsView(services, this::showHome)); break;
        }

        centerBox.getChildren().add(viewArea);
        setCenter(centerBox);

        // show small bottom buttons docked to the bottom
        setBottom(smallBottomGrid);
    }

    private GridPane createSmallBottomGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(6);
        grid.setPadding(new Insets(8));
        grid.setAlignment(Pos.CENTER);

        Button s1 = createSmallButton("Dashboard", "/icons/dashboard.png", "🏠");
        Button s2 = createSmallButton("Préstamos", "/icons/loan.png", "📦");
        Button s3 = createSmallButton("Incidencias", "/icons/incident.png", "🔧");
        Button s4 = createSmallButton("Personas", "/icons/persons.png", "👥");
        Button s5 = createSmallButton("Informes", "/icons/reports.png", "📊");

        grid.add(s1, 0, 0);
        grid.add(s2, 1, 0);
        grid.add(s3, 2, 0);
        grid.add(s4, 1, 1);
        grid.add(s5, 2, 1);

        s1.setOnAction(e -> activateOption("dashboard"));
        s2.setOnAction(e -> activateOption("loan"));
        s3.setOnAction(e -> activateOption("incident"));
        s4.setOnAction(e -> activateOption("persons"));
        s5.setOnAction(e -> activateOption("reports"));

        return grid;
    }

    private StackPane createHeroNode(String resourcePath, String labelText) {
        StackPane hero = new StackPane();
        hero.setPadding(new Insets(6));
        hero.getStyleClass().add("content-panel");

        try {
            java.net.URL url = getClass().getResource(resourcePath);
            if (url != null) {
                Image img = new Image(url.toExternalForm(), 900, 220, true, true);
                ImageView iv = new ImageView(img);
                hero.getChildren().add(iv);
                return hero;
            }
        } catch (Exception ignored) {}

        // subtle placeholder (no big letter)
        Rectangle r = new Rectangle(900, 220, Color.web("#EAF7FF"));
        r.setArcWidth(12); r.setArcHeight(12);
        if (labelText != null && !labelText.isEmpty()) {
            Label lbl = new Label(labelText);
            lbl.getStyleClass().add("app-subtitle");
            hero.getChildren().addAll(r, lbl);
        } else {
            hero.getChildren().add(r);
        }
        return hero;
    }

    private Button createBigButton(String text, String iconPath, String emoji) {
        Button b = new Button(text);
        b.getStyleClass().add("big-menu-button");
        Tooltip.install(b, new Tooltip(text));
        try {
            java.net.URL url = getClass().getResource(iconPath);
            if (url != null) {
                Image img = new Image(url.toExternalForm(), 84, 84, true, true);
                ImageView iv = new ImageView(img);
                b.setGraphic(iv);
            } else throw new RuntimeException("no icon");
        } catch (Exception ex) {
            Label emojiLabel = new Label(emoji);
            emojiLabel.getStyleClass().add("icon-emoji");
            StackPane icon = new StackPane();
            Rectangle rect = new Rectangle(90,90, Color.web("#00a0ff"));
            rect.setArcWidth(18); rect.setArcHeight(18);
            icon.getChildren().addAll(rect, emojiLabel);
            b.setGraphic(icon);
        }
        b.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        return b;
    }

    private Button createSmallButton(String text, String iconPath, String emoji) {
        Button b = new Button();
        b.getStyleClass().add("small-menu-button");
        Tooltip.install(b, new Tooltip(text));
        try {
            java.net.URL url = getClass().getResource(iconPath);
            if (url != null) {
                Image img = new Image(url.toExternalForm(), 36, 36, true, true);
                ImageView iv = new ImageView(img);
                b.setGraphic(iv);
            } else throw new RuntimeException("no icon");
        } catch (Exception ex) {
            Label emojiLabel = new Label(emoji);
            emojiLabel.getStyleClass().add("icon-emoji");
            b.setGraphic(emojiLabel);
        }
        return b;
    }

}
