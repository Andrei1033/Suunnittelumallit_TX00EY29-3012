package Assignment_07_State.state;

import Assignment_07_State.character.GameCharacter;

public class NoviceState implements CharacterState {

    private static final int TRAINING_XP = 25;
    private static final int INTERMEDIATE_XP = 100;

    private GameCharacter character;

    public NoviceState(GameCharacter character) {
        this.character = character;
    }

    @Override
    public void train() {
        character.addExperience(TRAINING_XP);

        System.out.println("The character " + character.getName() + " is trained.");
        System.out.println("+" + TRAINING_XP + " XP");

        if (character.getExperience() >= INTERMEDIATE_XP) {
            System.out.println("Congratulations! You reached Intermediate level!");
            character.setCurrentState(new IntermediateState(character));
        }
    }

    @Override
    public void meditate() {
        System.out.println("The character " + character.getName() + " cannot meditate yet.");
    }

    @Override
    public void fight() {
        System.out.println("The character " + character.getName() + " cannot fight yet.");
    }
}
