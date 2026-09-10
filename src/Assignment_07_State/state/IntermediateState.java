package Assignment_07_State.state;

import Assignment_07_State.character.GameCharacter;

public class IntermediateState implements CharacterState {

    private static final int TRAINING_XP = 25;
    private static final int MEDITATION_XP = 5;
    private static final int MEDITATION_HEALTH = 20;
    private static final int EXPERT_XP = 500;

    private GameCharacter character;

    public IntermediateState(GameCharacter character) {
        this.character = character;
    }

    @Override
    public void train() {
        character.addExperience(TRAINING_XP);
        System.out.println("The character " + character.getName() + " is trained.");
        System.out.println("+" + TRAINING_XP + " XP");
        checkLevelUp();
    }

    @Override
    public void meditate() {
        character.addExperience(MEDITATION_XP);
        character.addHealth(MEDITATION_HEALTH);
        System.out.println("The character " + character.getName() + " is meditated.");
        System.out.println("+" + MEDITATION_XP + " XP");
        System.out.println("+" + MEDITATION_HEALTH + " HP");
        checkLevelUp();
    }

    @Override
    public void fight() {
        System.out.println("The character " + character.getName() + " cannot fight yet.");
    }

    public void checkLevelUp() {
        if (character.getExperience() >= EXPERT_XP) {
            System.out.println("Congratulations! You reached Expert level!");
            character.setCurrentState(new ExpertState(character));
        }
    }
}

