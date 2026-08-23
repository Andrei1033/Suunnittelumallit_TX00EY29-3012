package Assignment_01_Factory_method.tile;

public class Building extends Tile {
    @Override
    public char getCharacter() {
        return 'B';
    }

    @Override
    public String getType() {
        return "Building";
    }
}