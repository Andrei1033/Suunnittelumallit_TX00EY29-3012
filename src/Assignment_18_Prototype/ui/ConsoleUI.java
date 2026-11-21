package Assignment_18_Prototype.ui;

import Assignment_18_Prototype.model.Book;
import Assignment_18_Prototype.model.Recommendation;
import Assignment_18_Prototype.service.RecommendationManager;

import java.util.Scanner;

public class ConsoleUI {

    private RecommendationManager manager;
    private Scanner scanner;

    public ConsoleUI(RecommendationManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n===== BOOK RECOMMENDATION SYSTEM =====");
            System.out.println("1. View recommendations");
            System.out.println("2. Create recommendation");
            System.out.println("3. Clone recommendation");
            System.out.println("4. Modify recommendation");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    manager.displayRecommendations();
                    break;

                case 2:
                    createRecommendation();
                    break;

                case 3:
                    cloneRecommendation();
                    break;

                case 4:
                    modifyRecommendation();
                    break;

                case 5:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Create new recommendation
    private void createRecommendation() {

        System.out.print("Enter target audience: ");
        String audience = scanner.nextLine();

        Recommendation recommendation =
                new Recommendation(audience);

        addBooks(recommendation);

        manager.addRecommendation(recommendation);

        System.out.println("Recommendation saved!");
    }

    // Clone existing recommendation
    private void cloneRecommendation() {

        manager.displayRecommendations();

        System.out.print("\nEnter recommendation number to clone: ");

        int index = readInt() - 1;

        Recommendation cloned =
                manager.cloneRecommendation(index);

        if (cloned == null) {
            System.out.println("Invalid recommendation number.");
            return;
        }

        System.out.print("Enter new target audience: ");
        cloned.setTargetAudience(scanner.nextLine());

        System.out.println("\nModify the cloned recommendation:");

        modifyBooks(cloned);

        manager.addRecommendation(cloned);

        System.out.println("Cloned recommendation saved!");
    }

    // Modify existing recommendation
    private void modifyRecommendation() {

        manager.displayRecommendations();

        System.out.print("\nEnter recommendation number: ");

        int index = readInt() - 1;

        Recommendation recommendation =
                manager.getRecommendation(index);

        if (recommendation == null) {
            System.out.println("Invalid recommendation number.");
            return;
        }

        System.out.println("1. Change target audience");
        System.out.println("2. Add a book");
        System.out.println("3. Remove a book");

        System.out.print("Choose an option: ");

        int choice = readInt();

        switch (choice) {

            case 1:
                System.out.print("New target audience: ");
                recommendation.setTargetAudience(scanner.nextLine());
                break;

            case 2:
                addBooks(recommendation);
                break;

            case 3:
                System.out.print("Enter book title to remove: ");
                String title = scanner.nextLine();

                if (recommendation.removeBook(title)) {
                    System.out.println("Book removed.");
                } else {
                    System.out.println("Book not found.");
                }
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    // Add one or more books
    private void addBooks(Recommendation recommendation) {

        boolean adding = true;

        while (adding) {

            System.out.print("Book title: ");
            String title = scanner.nextLine();

            System.out.print("Author: ");
            String author = scanner.nextLine();

            System.out.print("Genre: ");
            String genre = scanner.nextLine();

            System.out.print("Publication year: ");
            int year = readInt();

            Book book = new Book(
                    author, title, genre, year
            );

            recommendation.addBook(book);

            System.out.print("Add another book? (y/n): ");

            String answer = scanner.nextLine();

            if (!answer.equalsIgnoreCase("y")) {
                adding = false;
            }
        }
    }

    // Modify books in a cloned recommendation
    private void modifyBooks(Recommendation recommendation) {

        boolean modifying = true;

        while (modifying) {

            System.out.println("\n1. Add a book");
            System.out.println("2. Remove a book");
            System.out.println("3. Finish modifications");

            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    addBooks(recommendation);
                    break;

                case 2:
                    System.out.print("Book title to remove: ");

                    String title = scanner.nextLine();

                    if (recommendation.removeBook(title)) {
                        System.out.println("Book removed.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 3:
                    modifying = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Read integer safely
    private int readInt() {

        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }

        int number = scanner.nextInt();
        scanner.nextLine();

        return number;
    }
}