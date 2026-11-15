package org.example.factory;

import org.example.model.TerrainType;
import org.example.model.Tile;

public class RandomTileFactory extends TileFactory {

    @Override
    protected Tile createTile(TerrainType type) {
        return new Tile(type);
    }
}
