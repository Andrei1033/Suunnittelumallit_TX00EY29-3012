package Assignment_01_Factory_method.map;

import java.util.Scanner;

import Assignment_01_Factory_method.tile.Building;
import Assignment_01_Factory_method.tile.Forest;
import Assignment_01_Factory_method.tile.Road;
import Assignment_01_Factory_method.tile.Tile;

import java.util.Random;

public class WildernessMap extends Map {

    private static final Scanner scanner = new Scanner(System.in);

    private final int WIDTH;
    private final int HEIGHT;

    private final Tile[][] tiles;
    private final Random random = new Random();

    public WildernessMap() {
        System.out.print("Enter a WildernessMap width: ");
        this.WIDTH = scanner.nextInt();

        System.out.print("Enter a WildernessMap height: ");
        this.HEIGHT = scanner.nextInt();

        this.tiles = new Tile[this.HEIGHT][this.WIDTH];

        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                tiles[row][col] = createTile();
            }
        }
    }

    @Override
    public Tile createTile(){
        int tileType = random.nextInt(3);

        return switch (tileType) {
            case 0 -> new Road();
            case 1 -> new Forest();
            case 2 -> new Building();
            default -> throw new IllegalStateException("Invalid tile type");
        };
    }

    @Override
    public void display(){
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                System.out.print(tiles[row][col].getCharacter() + " ");
            }
            System.out.println();
        }
    }
}
