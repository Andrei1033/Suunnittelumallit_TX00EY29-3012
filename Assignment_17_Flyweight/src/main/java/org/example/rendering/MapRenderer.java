package org.example.rendering;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.example.model.Map;
import org.example.model.Tile;

public class MapRenderer {

    private static final int TILE_SIZE = 40;

    private final TileGraphicFactory graphicFactory;

    public MapRenderer(TileGraphicFactory graphicFactory) {
        this.graphicFactory = graphicFactory;
    }

    public Canvas render(Map map) {

        int width = map.getColumns() * TILE_SIZE;
        int height = map.getRows() * TILE_SIZE;

        Canvas canvas = new Canvas(width, height);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (int row = 0; row < map.getRows(); row++) {

            for (int column = 0;
                 column < map.getColumns();
                 column++) {

                Tile tile = map.getTileAt(row, column);

                TileGraphic graphic =
                        graphicFactory.getGraphic(
                                tile.getTerrainType()
                        );

                double x = column * TILE_SIZE;
                double y = row * TILE_SIZE;

                graphic.draw(gc, x, y, TILE_SIZE);
            }
        }

        return canvas;
    }
}