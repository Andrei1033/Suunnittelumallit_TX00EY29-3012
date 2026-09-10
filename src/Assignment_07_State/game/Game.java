package Assignment_07_State.game;

import Assignment_07_State.character.GameCharacter;
import Assignment_07_State.state.ExpertState;
import Assignment_07_State.state.IntermediateState;
import Assignment_07_State.state.MasterState;
import Assignment_07_State.state.NoviceState;

import java.util.Scanner;

public class Game {

    private GameCharacter character;
    private Scanner scanner;

    public Game(GameCharacter character) {
        this.character = character;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        boolean gameRunning = true;

        while (gameRunning) {

            displayStatus();

            if (character.getCurrentState() instanceof MasterState) {
                System.out.println("Congratulations!");
                System.out.println("You have reached Master level!");
                System.out.println("Game over.");
                break;
            }

            displayActions();

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    character.getCurrentState().train();
                    break;

                case 2:
                    character.getCurrentState().meditate();
                    break;

                case 3:
                    character.getCurrentState().fight();
                    break;

                case 4:
                    gameRunning = false;
                    System.out.println("Thanks for playing!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

            if (character.getHealth() <= 0) {
                System.out.println("Your character has died. Game over.");
                break;
            }

            System.out.println();
        }
    }

    private void displayStatus() {
        System.out.println("==============================");
        System.out.println("Character: " + character.getName());
        System.out.println("XP: " + character.getExperience());
        System.out.println("HP: " + character.getHealth());
        System.out.println("Level: " + getLevelName());
        System.out.println("==============================");
    }

    private void displayActions() {
        System.out.println("Available actions:");

        if (character.getCurrentState() instanceof NoviceState) {
            System.out.println("1. Train");
            System.out.println("4. Exit");

        } else if (character.getCurrentState() instanceof IntermediateState) {
            System.out.println("1. Train");
            System.out.println("2. Meditate");
            System.out.println("4. Exit");

        } else if (character.getCurrentState() instanceof ExpertState) {
            System.out.println("1. Train");
            System.out.println("2. Meditate");
            System.out.println("3. Fight");
            System.out.println("4. Exit");
        }

        System.out.print("Choose an action: ");
    }

    private String getLevelName() {

        if (character.getCurrentState() instanceof NoviceState) {
            return "Novice";

        } else if (character.getCurrentState() instanceof IntermediateState) {
            return "Intermediate";

        } else if (character.getCurrentState() instanceof ExpertState) {
            return "Expert";

        } else if (character.getCurrentState() instanceof MasterState) {
            return "Master";
        }

        return "Unknown";
    }
}
