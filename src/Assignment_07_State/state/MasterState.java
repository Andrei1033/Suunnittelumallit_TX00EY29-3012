package Assignment_07_State.state;

import Assignment_07_State.character.GameCharacter;

public class MasterState implements CharacterState {
    private GameCharacter character;

    public MasterState(GameCharacter character) {
        this.character = character;
    }

    @Override
    public void train() {
        System.out.println("The character " + character.getName() + " has reached the Master level. The game is over.");
    }

    @Override
    public void meditate() {
        System.out.println("The character " + character.getName() + " has reached the Master level. The game is over.");
    }

    @Override
    public void fight() {
        System.out.println("The character " + character.getName() + " has reached the Master level. The game is over.");
    }
}



























































