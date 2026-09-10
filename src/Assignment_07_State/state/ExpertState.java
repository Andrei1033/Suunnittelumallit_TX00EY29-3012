package Assignment_07_State.state;

import Assignment_07_State.character.GameCharacter;

import java.util.concurrent.ThreadLocalRandom;

public class ExpertState implements CharacterState {

    private static final int TRAINING_XP = 25;
    private static final int MEDITATION_XP = 5;
    private static final int MEDITATION_HEALTH = 20;

    private static final int FIGHTING_XP = 50;

    private static final int MASTER_XP = 1500;

    private GameCharacter character;

    public ExpertState(GameCharacter character) {
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

        int FIGHTING_HEALTH_LOSS = ThreadLocalRandom.current().nextInt(0, 21);

        character.addExperience(FIGHTING_XP);
        character.reduceHealth(FIGHTING_HEALTH_LOSS);
        System.out.println("The character " + character.getName() + " is fighting.");
        System.out.println("+" + FIGHTING_XP + " XP");
        System.out.println("-" + FIGHTING_HEALTH_LOSS + " HP");
        checkLevelUp();
    }

    private void checkLevelUp() {
        if (character.getExperience() >= MASTER_XP) {
            System.out.println("Congratulations! You reached Master level!");
            character.setCurrentState(new MasterState(character));
        }
    }
}
