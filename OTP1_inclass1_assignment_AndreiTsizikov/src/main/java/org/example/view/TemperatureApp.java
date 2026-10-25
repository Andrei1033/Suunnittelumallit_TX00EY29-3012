package org.example.view;

import org.example.I18n.I18n;
import org.example.controller.TemperatureController;

import javafx.application.Application;
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
import java.util.Locale;

public class TemperatureApp extends Application {

    private final TemperatureController controller = new TemperatureController();
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        try {
            DBConnection.initialize();
        } catch (SQLException e) {
            showFatalError("error.db.init", e);
            return;
        }
        buildUi();
    }

    private void buildUi() {
        // --- Language selector (order matters for bundle naming) ---
        ComboBox<Locale> languageBox = new ComboBox<>();
        languageBox.getItems().addAll(
                Locale.US,
                new Locale("fi", "FI"),
                new Locale("sv", "SE"),
                new Locale("ja", "JP")
        );
        languageBox.setValue(I18n.getLocale());
        languageBox.setOnAction(e -> {
            I18n.setLocale(languageBox.getValue());
            buildUi(); // rebuild UI with the new locale
        });

        // --- Input controls ---
        ComboBox<TemperatureUnit> fromBox = new ComboBox<>();
        ComboBox<TemperatureUnit> toBox = new ComboBox<>();
        TextField valueField = new TextField();
        valueField.setPromptText(I18n.get("field.value.prompt"));
        Button convertButton = new Button(I18n.get("button.convert"));

        Label resultLabel = new Label(I18n.get("label.result.empty"));
        resultLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label extremeLabel = new Label("");
        extremeLabel.setStyle("-fx-text-fill: darkred;");

        // --- Layout for input row ---
        HBox inputRow = new HBox(10,
                new Label(I18n.get("label.from")), fromBox,
                new Label(I18n.get("label.to")), toBox,
                new Label(I18n.get("label.value")), valueField,
                convertButton);
        inputRow.setAlignment(Pos.CENTER_LEFT);
        inputRow.setPadding(new Insets(10));

        // --- History table ---
        TableView<TempRecord> table = new TableView<>();

        TableColumn<TempRecord, String> timeCol = new TableColumn<>(I18n.get("table.column.time"));
        timeCol.setCellValueFactory(cell -> {
            var ts = cell.getValue().getCreatedAt();
            String formatted = ts == null ? "" :
                    ts.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new javafx.beans.property.SimpleStringProperty(formatted);
        });

        TableColumn<TempRecord, String> fromCol = new TableColumn<>(I18n.get("table.column.from"));
        fromCol.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getSourceUnit().getName()));

        TableColumn<TempRecord, String> toCol = new TableColumn<>(I18n.get("table.column.to"));
        toCol.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getTargetUnit().getName()));

        TableColumn<TempRecord, Number> inputCol = new TableColumn<>(I18n.get("table.column.input"));
        inputCol.setCellValueFactory(new PropertyValueFactory<>("inputValue"));

        TableColumn<TempRecord, Number> outputCol = new TableColumn<>(I18n.get("table.column.output"));
        outputCol.setCellValueFactory(new PropertyValueFactory<>("outputValue"));

        table.getColumns().addAll(timeCol, fromCol, toCol, inputCol, outputCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(300);

        HBox topRow = new HBox(10, new Label("🌐"), languageBox);
        topRow.setAlignment(Pos.CENTER_RIGHT);

        VBox root = new VBox(10,
                topRow,
                inputRow,
                resultLabel,
                extremeLabel,
                new Label(I18n.get("label.history")),
                table);
        root.setPadding(new Insets(15));

        controller.init(fromBox, toBox, valueField, resultLabel, extremeLabel, table);

        convertButton.setOnAction(e -> controller.onConvert());
        valueField.setOnAction(e -> controller.onConvert());

        stage.setTitle(I18n.get("app.title"));
        stage.setScene(new Scene(root, 700, 520));
        stage.show();
    }


    private void showFatalError(String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(I18n.get("error.title"));
        alert.setHeaderText(message);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
