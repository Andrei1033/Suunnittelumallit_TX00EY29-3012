package org.example.Gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Controller.Controller;
import org.example.Memento.IMemento;

public class HistoryWindow {

    public static void show(Controller controller) {
        Stage stage = new Stage();
        stage.setTitle("State History");

        ObservableList<IMemento> items =
                FXCollections.observableArrayList(controller.getHistory());

        ListView<IMemento> listView = new ListView<>(items);
        listView.setPrefSize(560, 300);

        listView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(IMemento item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getLabel());
            }
        });

        // Fire only on real selection changes, not on stray clicks on empty area.
        listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldIdx, newIdx) -> {
            int index = newIdx.intValue();
            if (index >= 0) {
                controller.restoreFromHistory(index);
            }
        });

        // Optional: still handle double-click explicitly? Not needed — selection listener suffices.

        VBox root = new VBox(listView);
        root.setPadding(new Insets(10));
        stage.setScene(new Scene(root));
        stage.show();
    }
}