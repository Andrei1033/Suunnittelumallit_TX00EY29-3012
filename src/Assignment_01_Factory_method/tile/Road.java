package Assignment_01_Factory_method.tile;

public class Road extends Tile {
    @Override
    public char getCharacter() {
        return 'R';
    }

    @Override
    public String getType() {
        return "Road";
    }
}