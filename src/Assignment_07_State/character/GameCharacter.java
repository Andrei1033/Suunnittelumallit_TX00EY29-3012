package Assignment_07_State.character;

import Assignment_07_State.state.CharacterState;
import Assignment_07_State.state.NoviceState;

public class GameCharacter {

    private String name;
    private int experience;
    private int health;
    private CharacterState currentState;

    public GameCharacter(String name, int experience, int health) {
        this.name = name;
        this.experience = experience;
        this.health = health;
        this.currentState = new NoviceState(this);
    }

    // Getters for name, experience, health, and currentState
    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public int getHealth() {
        return health;
    }

    public CharacterState getCurrentState() {
        return currentState;
    }

    // Setter for currentState
    public void setCurrentState(CharacterState currentState) {
        this.currentState = currentState;
    }

    // Methods to modify experience and health
    public void addExperience(int amount) {
        experience += amount;
    }

    public void addHealth(int amount) {
        health += amount;
    }

    public void reduceHealth(int amount) {
        health -= amount;

        if (health < 0) {
            health = 0;
        }
    }
}
