package org.example.rendering;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import org.example.model.TerrainType;

import java.net.URL;

import static org.example.model.TerrainType.GRASS;
import static org.example.model.TerrainType.WATER;

public class TileGraphic {

    private final Image image;

    public TileGraphic(TerrainType terrainType) {

        String imagePath = getImagePath(terrainType);

        URL resource = getClass().getResource(imagePath);

        if (resource == null) {
            throw new IllegalStateException(
                    "Image not found: " + imagePath
            );
        }

        this.image = new Image(resource.toExternalForm(), false);

        if (image.isError()) {
            throw new IllegalStateException(
                    "Could not load image: " + imagePath,
                    image.getException()
            );
        }
    }

    private String getImagePath(TerrainType terrainType) {

        switch (terrainType) {

            case GRASS:
                return "/images/grass.png";

            case WATER:
                return "/images/water.png";

            case MOUNTAIN:
                return "/images/mountain.png";

            case SAND:
                return "/images/sand.png";

            default:
                throw new IllegalArgumentException(
                        "Unknown terrain type: " + terrainType
                );
        }
    }

    public void draw(
            GraphicsContext gc,
            double x,
            double y,
            double size) {

        gc.drawImage(image, x, y, size, size);
    }
}