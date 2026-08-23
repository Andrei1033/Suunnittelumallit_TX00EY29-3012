package Assignment_01_Factory_method.tile;

public class Water extends Tile {
    @Override
    public char getCharacter() {
        return 'W';
    }

    @Override
    public String getType() {
        return "Water";
    }
}