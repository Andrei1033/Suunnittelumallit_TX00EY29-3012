package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.factory.RandomTileFactory;
import org.example.factory.TileFactory;
import org.example.model.Map;
import org.example.rendering.MapRenderer;
import org.example.rendering.TileGraphicFactory;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) {

        // PHASE 1: Generate the map

        TileFactory tileFactory = new RandomTileFactory();

        Map map = tileFactory.createMap(15, 20);


        // PHASE 2: Render the map

        TileGraphicFactory graphicFactory =
                new TileGraphicFactory();

        MapRenderer renderer =
                new MapRenderer(graphicFactory);

        Canvas canvas = renderer.render(map);


        // Display the map

        StackPane root = new StackPane(canvas);

        Scene scene = new Scene(root);

        stage.setTitle("RPG Map Generator");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


        // Display Flyweight statistics

        System.out.println(
                "Total tiles: " +
                        (map.getRows() * map.getColumns())
        );

        System.out.println(
                "Unique graphics created: " +
                        graphicFactory.getCreatedGraphicCount()
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}