package org.example.rendering;

import org.example.model.TerrainType;

import java.util.EnumMap;
import java.util.Map;

public class TileGraphicFactory {

    private final Map<TerrainType, TileGraphic> graphics =
            new EnumMap<>(TerrainType.class);

    public TileGraphic getGraphic(TerrainType type) {

        return graphics.computeIfAbsent(
                type,
                TileGraphic::new
        );
    }

    public int getCreatedGraphicCount() {
        return graphics.size();
    }
}