package org.example.controller;

import org.example.DBConnectors.TempRecord;
import org.example.DBConnectors.TemperatureUnit;
import org.example.DaoElements.TempRecordDAO;
import org.example.DaoElements.TemperatureUnitDAO;
import org.example.I18n.I18n;
import org.example.TemperatureConverter;
import org.example.view.FontSupport;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

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

    // State that survives a UI rebuild (language change)
    private String savedFromName;
    private String savedToName;
    private String savedValueText;
    private boolean hasResult;
    private double lastInput;
    private double lastOutput;
    private TemperatureUnit lastFrom;
    private TemperatureUnit lastTo;
    private boolean lastExtreme;

    /** Remember the current selections so they can be restored after the UI is rebuilt. */
    public void saveState() {
        if (fromBox == null) {
            return;
        }
        TemperatureUnit f = fromBox.getValue();
        TemperatureUnit t = toBox.getValue();
        savedFromName = f == null ? null : f.getName();
        savedToName = t == null ? null : t.getName();
        savedValueText = valueField.getText();
    }

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

        StringConverter<TemperatureUnit> unitConverter = new StringConverter<>() {
            @Override
            public String toString(TemperatureUnit u) {
                return u == null ? "" : I18n.unitName(u.getName()) + " (" + u.getSymbol() + ")";
            }

            @Override
            public TemperatureUnit fromString(String s) {
                return null;
            }
        };
        fromBox.setConverter(unitConverter);
        toBox.setConverter(unitConverter);

        loadUnits();
        if (savedValueText != null) {
            valueField.setText(savedValueText);
        }
        renderResult();
        loadHistory();
    }

    private void loadUnits() {
        try {
            List<TemperatureUnit> units = unitDAO.findAll();
            fromBox.setItems(FXCollections.observableArrayList(units));
            toBox.setItems(FXCollections.observableArrayList(units));

            if (!units.isEmpty()) {
                fromBox.getSelectionModel().select(
                        findByName(units, savedFromName != null ? savedFromName : TemperatureConverter.CELSIUS));
                toBox.getSelectionModel().select(
                        findByName(units, savedToName != null ? savedToName : TemperatureConverter.FAHRENHEIT));
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

    private void renderResult() {
        if (!hasResult) {
            return;
        }
        resultLabel.setText(String.format(I18n.getLocale(), "%.2f %s = %.2f %s",
                lastInput, lastFrom.getSymbol(), lastOutput, lastTo.getSymbol()));
        extremeLabel.setText(lastExtreme ? I18n.get("label.extreme.warning") : "");
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
            input = Double.parseDouble(raw.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            showError(I18n.get("error.value.not.number"), null);
            return;
        }

        // "NaN" and "Infinity" parse successfully but are not valid temperatures
        if (Double.isNaN(input) || Double.isInfinite(input)) {
            showError(I18n.get("error.value.not.number"), null);
            return;
        }

        if (TemperatureConverter.isBelowAbsoluteZero(input, from.getName())) {
            showError(I18n.get("error.below.absolute.zero"), null);
            return;
        }

        double output = TemperatureConverter.convert(input, from, to);

        hasResult = true;
        lastInput = input;
        lastOutput = output;
        lastFrom = from;
        lastTo = to;
        lastExtreme = TemperatureConverter.isExtreme(input, from.getName());
        renderResult();

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

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setStyle(FontSupport.styleFor(I18n.getLocale()));
        alert.setTitle(I18n.get("error.title"));
        alert.setHeaderText(message);
        alert.setContentText(e == null ? null : e.getMessage());
        alert.showAndWait();
    }
}
