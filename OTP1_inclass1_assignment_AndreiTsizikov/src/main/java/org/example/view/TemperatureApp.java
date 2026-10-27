package org.example.view;

import org.example.DBConnectors.DBConnection;
import org.example.DBConnectors.TempRecord;
import org.example.DBConnectors.TemperatureUnit;
import org.example.I18n.I18n;
import org.example.controller.TemperatureController;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TemperatureApp extends Application {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final TemperatureController controller = new TemperatureController();
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        FontSupport.load();
        try {
            DBConnection.initialize();
        } catch (SQLException e) {
            showFatalError("error.db.init", e);
            return;
        }
        buildUi();
    }

    private void buildUi() {
        // --- Language selector ---
        ComboBox<Locale> languageBox = new ComboBox<>();
        languageBox.getItems().addAll(I18n.SUPPORTED);
        languageBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Locale l) {
                // Show each language in its own language: English, suomi, svenska, 日本語
                return l == null ? "" : l.getDisplayLanguage(l);
            }

            @Override
            public Locale fromString(String s) {
                return null;
            }
        });
        languageBox.setValue(I18n.getLocale());
        languageBox.setOnAction(e -> {
            Locale selected = languageBox.getValue();
            if (selected == null || selected.equals(I18n.getLocale())) {
                return;
            }
            controller.saveState();   // keep input value and selected units
            I18n.setLocale(selected);
            buildUi();                // rebuild UI with the new locale
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
        Locale locale = I18n.getLocale();

        TableColumn<TempRecord, String> timeCol = new TableColumn<>(I18n.get("table.column.time"));
        timeCol.setCellValueFactory(cell -> {
            var ts = cell.getValue().getCreatedAt();
            return new SimpleStringProperty(ts == null ? "" : ts.format(TIME_FORMAT));
        });

        TableColumn<TempRecord, String> fromCol = new TableColumn<>(I18n.get("table.column.from"));
        fromCol.setCellValueFactory(cell ->
                new SimpleStringProperty(I18n.unitName(cell.getValue().getSourceUnit().getName())));

        TableColumn<TempRecord, String> toCol = new TableColumn<>(I18n.get("table.column.to"));
        toCol.setCellValueFactory(cell ->
                new SimpleStringProperty(I18n.unitName(cell.getValue().getTargetUnit().getName())));

        TableColumn<TempRecord, String> inputCol = new TableColumn<>(I18n.get("table.column.input"));
        inputCol.setCellValueFactory(cell ->
                new SimpleStringProperty(String.format(locale, "%.2f", cell.getValue().getInputValue())));
        inputCol.setStyle("-fx-alignment: CENTER-RIGHT;");

        TableColumn<TempRecord, String> outputCol = new TableColumn<>(I18n.get("table.column.output"));
        outputCol.setCellValueFactory(cell ->
                new SimpleStringProperty(String.format(locale, "%.2f", cell.getValue().getOutputValue())));
        outputCol.setStyle("-fx-alignment: CENTER-RIGHT;");

        table.getColumns().addAll(timeCol, fromCol, toCol, inputCol, outputCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(300);

        HBox topRow = new HBox(10, new Label(I18n.get("label.language")), languageBox);
        topRow.setAlignment(Pos.CENTER_RIGHT);

        VBox root = new VBox(10,
                topRow,
                inputRow,
                resultLabel,
                extremeLabel,
                new Label(I18n.get("label.history")),
                table);
        root.setPadding(new Insets(15));
        root.setStyle(FontSupport.styleFor(locale));

        controller.init(fromBox, toBox, valueField, resultLabel, extremeLabel, table);

        convertButton.setOnAction(e -> controller.onConvert());
        valueField.setOnAction(e -> controller.onConvert());

        stage.setTitle(I18n.get("app.title"));
        stage.setScene(new Scene(root, 760, 520));
        stage.show();
    }

    private void showFatalError(String messageKey, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setStyle(FontSupport.styleFor(I18n.getLocale()));
        alert.setTitle(I18n.get("error.title"));
        alert.setHeaderText(I18n.get(messageKey));
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
