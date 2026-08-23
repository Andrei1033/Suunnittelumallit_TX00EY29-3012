package Assignment_01_Factory_method.tile;

public class Swamp extends Tile {
    @Override
    public char getCharacter() {
        return 'S';
    }

    @Override
    public String getType() {
        return "Swamp";
    }
}