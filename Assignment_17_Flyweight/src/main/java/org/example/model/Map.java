package org.example.model;

public class Map {

    private final Tile[][] tiles;

    public Map(Tile[][] tiles) {
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
