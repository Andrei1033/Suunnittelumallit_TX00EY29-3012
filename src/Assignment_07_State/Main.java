package Assignment_07_State;

import Assignment_07_State.character.GameCharacter;
import Assignment_07_State.game.Game;

public class Main {
    public static void main(String[] args) {
        GameCharacter character =
                new GameCharacter("Hero", 0, 100);

        Game game = new Game(character);

        game.start();
    }
}
