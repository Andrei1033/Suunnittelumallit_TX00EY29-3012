package Assignment_01_Factory_method.tile;

public class Forest extends Tile{
    @Override
    public char getCharacter() {
        return 'F';
    }

    @Override
    public String getType() {
        return "Forest";
    }
}
