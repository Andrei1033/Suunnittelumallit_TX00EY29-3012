package org.example.Gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Controller.Controller;
import org.example.Memento.IMemento;

public class HistoryWindow {

    public static void show(Controller controller) {
        Stage stage = new Stage();
        stage.setTitle("State History");

        // Build an observable copy so the list view can refresh
        ObservableList<IMemento> items =
                FXCollections.observableArrayList(controller.getHistory());

        ListView<IMemento> listView = new ListView<>(items);
        listView.setPrefSize(520, 300);

        // Custom cell so we control the text shown
        listView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(IMemento item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getLabel());
            }
        });

        // Clicking an entry restores that state
        listView.setOnMouseClicked(event -> {
            int index = listView.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                controller.restoreFromHistory(index);
            }
        });

        VBox root = new VBox(listView);
        root.setPadding(new Insets(10));

        stage.setScene(new Scene(root));
        stage.show();
    }
}
