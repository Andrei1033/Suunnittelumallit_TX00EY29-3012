package org.example.view;

import controller.TemperatureController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.example.DBConnectors.DBConnection;
import org.example.DBConnectors.TempRecord;
import org.example.DBConnectors.TemperatureUnit;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class TemperatureApp extends Application {

    private final TemperatureController controller = new TemperatureController();

    @Override
    public void start(Stage stage) {
        try {
            DBConnection.initialize();
        } catch (SQLException e) {
            showFatalError("Database initialization failed", e);
            return;
        }

        // --- Input controls ---
        ComboBox<TemperatureUnit> fromBox = new ComboBox<>();
        ComboBox<TemperatureUnit> toBox = new ComboBox<>();
        TextField valueField = new TextField();
        valueField.setPromptText("Enter value");
        Button convertButton = new Button("Convert");

        Label resultLabel = new Label("—");
        resultLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label extremeLabel = new Label("");
        extremeLabel.setStyle("-fx-text-fill: darkred;");

        // --- Layout for input row ---
        HBox inputRow = new HBox(10,
                new Label("From:"), fromBox,
                new Label("To:"), toBox,
                new Label("Value:"), valueField,
                convertButton);
        inputRow.setAlignment(Pos.CENTER_LEFT);
        inputRow.setPadding(new Insets(10));

        // --- History table ---
        TableView<TempRecord> table = new TableView<>();

        TableColumn<TempRecord, String> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(cell -> {
            var ts = cell.getValue().getCreatedAt();
            String formatted = ts == null ? "" :
                    ts.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new javafx.beans.property.SimpleStringProperty(formatted);
        });

        TableColumn<TempRecord, String> fromCol = new TableColumn<>("From");
        fromCol.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getSourceUnit().getName()));

        TableColumn<TempRecord, String> toCol = new TableColumn<>("To");
        toCol.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getTargetUnit().getName()));

        TableColumn<TempRecord, Number> inputCol = new TableColumn<>("Input");
        inputCol.setCellValueFactory(new PropertyValueFactory<>("inputValue"));

        TableColumn<TempRecord, Number> outputCol = new TableColumn<>("Output");
        outputCol.setCellValueFactory(new PropertyValueFactory<>("outputValue"));

        table.getColumns().addAll(timeCol, fromCol, toCol, inputCol, outputCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(300);

        // --- Root layout ---
        VBox root = new VBox(10,
                inputRow,
                resultLabel,
                extremeLabel,
                new Label("History:"),
                table);
        root.setPadding(new Insets(15));

        // --- Controller wiring ---
        controller.init(fromBox, toBox, valueField, resultLabel, extremeLabel, table);

        convertButton.setOnAction(e -> controller.onConvert());
        valueField.setOnAction(e -> controller.onConvert());

        // --- Stage ---
        stage.setTitle("Temperature Converter");
        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }

    private void showFatalError(String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
