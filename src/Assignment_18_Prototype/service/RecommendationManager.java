package Assignment_18_Prototype.service;

import Assignment_18_Prototype.model.Book;
import Assignment_18_Prototype.model.Recommendation;

import java.util.ArrayList;
import java.util.List;

public class RecommendationManager {

    private List<Recommendation> recommendations;

    public RecommendationManager() {
        recommendations = new ArrayList<>();

        createInitialRecommendations();
    }

    // Add initial recommendations
    private void createInitialRecommendations() {

        Recommendation fantasy =
                new Recommendation("Fantasy readers");

        fantasy.addBook(new Book(
                "J.R.R. Tolkien", "The Hobbit",
                "Fantasy", 1937
        ));

        fantasy.addBook(new Book(
                "J.K. Rowling", "Harry Potter",
                "Fantasy", 1997
        ));

        addRecommendation(fantasy);

        Recommendation mystery =
                new Recommendation("Mystery lovers");

        mystery.addBook(new Book(
                "Agatha Christie", "Murder on the Orient Express",
                "Mystery", 1934
        ));

        mystery.addBook(new Book(
                "Arthur Conan Doyle", "The Hound of the Baskervilles",
                "Mystery", 1902
        ));

        addRecommendation(mystery);
    }

    // Add recommendation
    public void addRecommendation(Recommendation recommendation) {
        recommendations.add(recommendation);
    }

    // Get all recommendations
    public List<Recommendation> getRecommendations() {
        return new ArrayList<>(recommendations);
    }

    // Display recommendations
    public void displayRecommendations() {

        if (recommendations.isEmpty()) {
            System.out.println("No recommendations available.");
            return;
        }

        for (int i = 0; i < recommendations.size(); i++) {

            System.out.println("\nRecommendation #" + (i + 1));

            System.out.println(recommendations.get(i));
        }
    }

    // Find recommendation by index
    public Recommendation getRecommendation(int index) {

        if (index < 0 || index >= recommendations.size()) {
            return null;
        }

        return recommendations.get(index);
    }

    // Clone recommendation
    public Recommendation cloneRecommendation(int index) {

        Recommendation original = getRecommendation(index);

        if (original == null) {
            return null;
        }

        return original.clone();
    }
}