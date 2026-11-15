package org.example.rendering;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import org.example.model.TerrainType;

import static org.example.model.TerrainType.GRASS;
import static org.example.model.TerrainType.WATER;

public class TileGraphic {

    private final TerrainType terrainType;
    private final Color baseColor;

    public TileGraphic(TerrainType terrainType) {

        this.terrainType = terrainType;

        switch (terrainType) {
            case GRASS:
                baseColor = Color.FORESTGREEN;
                break;

            case WATER:
                baseColor = Color.DODGERBLUE;
                break;

            case MOUNTAIN:
                baseColor = Color.LIGHTGRAY;
                break;

            case SAND:
                baseColor = Color.KHAKI;
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown terrain type"
                );
        }
    }

    public void draw(
            GraphicsContext gc,
            double x,
            double y,
            double size) {

        gc.setFill(baseColor);
        gc.fillRect(x, y, size, size);

        switch (terrainType) {

            case GRASS:
                gc.setFill(Color.DARKGREEN);
                gc.fillOval(
                        x + size * 0.25,
                        y + size * 0.25,
                        size * 0.15,
                        size * 0.15
                );
                gc.fillOval(
                        x + size * 0.65,
                        y + size * 0.60,
                        size * 0.12,
                        size * 0.12
                );
                break;

            case WATER:
                gc.setStroke(Color.LIGHTBLUE);
                gc.setLineWidth(2);

                gc.strokeLine(
                        x + size * 0.15,
                        y + size * 0.35,
                        x + size * 0.75,
                        y + size * 0.35
                );

                gc.strokeLine(
                        x + size * 0.30,
                        y + size * 0.65,
                        x + size * 0.85,
                        y + size * 0.65
                );
                break;

            case MOUNTAIN:
                gc.setFill(Color.DARKSLATEGRAY);

                gc.fillPolygon(
                        new double[]{
                                x + size * 0.15,
                                x + size * 0.50,
                                x + size * 0.85
                        },
                        new double[]{
                                y + size * 0.85,
                                y + size * 0.15,
                                y + size * 0.85
                        },
                        3
                );
                break;

            case SAND:
                gc.setFill(Color.SANDYBROWN);

                gc.fillOval(
                        x + size * 0.25,
                        y + size * 0.30,
                        size * 0.08,
                        size * 0.08
                );

                gc.fillOval(
                        x + size * 0.65,
                        y + size * 0.70,
                        size * 0.08,
                        size * 0.08
                );
                break;
        }

        // Draw tile borders
        gc.setStroke(Color.rgb(0, 0, 0, 0.25));
        gc.setLineWidth(1);
        gc.strokeRect(x, y, size, size);
    }
}