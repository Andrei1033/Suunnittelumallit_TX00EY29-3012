package Assignment_18_Prototype.model;

public class Book implements Cloneable {

    private String author;
    private String title;
    private String genre;
    private int publicationYear;

    public Book(String author, String title,
                String genre, int publicationYear) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    // Getters
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    // Setters
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    // Prototype method
    @Override
    public Book clone() {
        return new Book(
                this.author,
                this.title,
                this.genre,
                this.publicationYear
        );
    }

    @Override
    public String toString() {
        return title + " by " + author +
                " (" + genre + ", " + publicationYear + ")";
    }
}