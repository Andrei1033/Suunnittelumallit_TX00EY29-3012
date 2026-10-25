package org.example.controller;

import org.example.DBConnectors.TempRecord;
import org.example.DBConnectors.TemperatureUnit;
import org.example.DaoElements.TempRecordDAO;
import org.example.DaoElements.TemperatureUnitDAO;
import org.example.I18n.I18n;
import org.example.TemperatureConverter;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class TemperatureController {

    private final TemperatureUnitDAO unitDAO = new TemperatureUnitDAO();
    private final TempRecordDAO recordDAO = new TempRecordDAO();

    private ComboBox<TemperatureUnit> fromBox;
    private ComboBox<TemperatureUnit> toBox;
    private TextField valueField;
    private Label resultLabel;
    private Label extremeLabel;
    private TableView<TempRecord> table;

    public void init(ComboBox<TemperatureUnit> fromBox,
                     ComboBox<TemperatureUnit> toBox,
                     TextField valueField,
                     Label resultLabel,
                     Label extremeLabel,
                     TableView<TempRecord> table) {

        this.fromBox = fromBox;
        this.toBox = toBox;
        this.valueField = valueField;
        this.resultLabel = resultLabel;
        this.extremeLabel = extremeLabel;
        this.table = table;

        loadUnits();
        loadHistory();
    }

    private void loadUnits() {
        try {
            List<TemperatureUnit> units = unitDAO.findAll();
            fromBox.setItems(FXCollections.observableArrayList(units));
            toBox.setItems(FXCollections.observableArrayList(units));

            if (!units.isEmpty()) {
                fromBox.getSelectionModel().select(findByName(units, TemperatureConverter.CELSIUS));
                toBox.getSelectionModel().select(findByName(units, TemperatureConverter.FAHRENHEIT));
            }
        } catch (SQLException e) {
            showError(I18n.get("error.load.units"), e);
        }
    }

    private TemperatureUnit findByName(List<TemperatureUnit> units, String name) {
        return units.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst()
                .orElse(units.get(0));
    }

    private void loadHistory() {
        try {
            List<TempRecord> records = recordDAO.findAll();
            table.setItems(FXCollections.observableArrayList(records));
        } catch (SQLException e) {
            showError(I18n.get("error.load.history"), e);
        }
    }

    public void onConvert() {
        TemperatureUnit from = fromBox.getValue();
        TemperatureUnit to = toBox.getValue();

        if (from == null || to == null) {
            showError(I18n.get("error.select.units"), null);
            return;
        }

        String raw = valueField.getText();
        if (raw == null || raw.isBlank()) {
            showError(I18n.get("error.enter.value"), null);
            return;
        }

        double input;
        try {
            input = Double.parseDouble(raw.replace(',', '.'));
        } catch (NumberFormatException e) {
            showError(I18n.get("error.value.not.number"), null);
            return;
        }

        double output = TemperatureConverter.convert(input, from, to);

        resultLabel.setText(String.format("%.2f %s = %.2f %s",
                input, from.getSymbol(), output, to.getSymbol()));

        boolean extreme = TemperatureConverter.isExtreme(input, from.getName());
        extremeLabel.setText(extreme ? I18n.get("label.extreme.warning") : "");

        try {
            TempRecord record = new TempRecord(from, to, input, output);
            recordDAO.save(record);
            loadHistory();
        } catch (SQLException e) {
            showError(I18n.get("error.save.record"), e);
        }
    }

    private void showError(String message, Exception e) {
        String details = (e == null) ? "" : (": " + e.getMessage());
        System.err.println(message + details);

        javafx.scene.control.Alert alert =
                new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(I18n.get("error.title"));
        alert.setHeaderText(message);
        alert.setContentText(e == null ? null : e.getMessage());
        alert.showAndWait();
    }
}