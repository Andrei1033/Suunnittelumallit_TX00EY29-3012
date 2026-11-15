package org.example.factory;

import org.example.model.Map;
import org.example.model.TerrainType;
import org.example.model.Tile;

import java.util.Random;

public abstract class TileFactory {

    private final Random random = new Random();

    public Map createMap(int rows, int columns) {

        Tile[][] tiles = new Tile[rows][columns];

        TerrainType[] terrainTypes = TerrainType.values();

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {

                TerrainType type = terrainTypes[
                        random.nextInt(terrainTypes.length)
                        ];

                tiles[row][column] = createTile(type);
            }
        }

        return new Map(tiles);
    }

    protected abstract Tile createTile(TerrainType type);
}
