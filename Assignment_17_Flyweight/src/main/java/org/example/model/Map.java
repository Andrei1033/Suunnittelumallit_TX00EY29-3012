package org.example.model;

public class Map {

    private final Tile[][] tiles;

    public Map(Tile[][] tiles) {
        if (tiles == null || tiles.length == 0
                || tiles[0] == null || tiles[0].length == 0) {
            throw new IllegalArgumentException("Map cannot be empty");
        }

        int columns = tiles[0].length;

        for (Tile[] row : tiles) {
            if (row == null || row.length != columns) {
                throw new IllegalArgumentException(
                        "Map must be rectangular"
                );
            }
        }

        this.tiles = tiles;
    }

    public int getRows() {
        return tiles.length;
    }

    public int getColumns() {
        return tiles[0].length;
    }

    public Tile getTileAt(int row, int column) {
        return tiles[row][column];
    }
}
