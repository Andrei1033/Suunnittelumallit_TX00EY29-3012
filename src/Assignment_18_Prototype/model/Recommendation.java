package Assignment_18_Prototype.model;

import java.util.ArrayList;
import java.util.List;

public class Recommendation {

    private String targetAudience;
    private List<Book> books;

    public Recommendation(String targetAudience) {
        this.targetAudience = targetAudience;
        this.books = new ArrayList<>();
    }

    // Getters
    public String getTargetAudience() {
        return targetAudience;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    // Change target audience
    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    // Add a book
    public void addBook(Book book) {
        books.add(book);
    }

    // Remove a book by title
    public boolean removeBook(String title) {
        return books.removeIf(
                book -> book.getTitle().equalsIgnoreCase(title)
        );
    }

    // Deep clone
    public Recommendation clone() {

        Recommendation cloned =
                new Recommendation(this.targetAudience);

        for (Book book : this.books) {
            cloned.addBook(book.clone());
        }

        return cloned;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        result.append("Target audience: ")
                .append(targetAudience)
                .append("\nBooks:\n");

        for (Book book : books) {
            result.append(" - ")
                    .append(book)
                    .append("\n");
        }

        return result.toString();
    }
}