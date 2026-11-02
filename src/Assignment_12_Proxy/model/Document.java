package Assignment_12_Proxy.model;

import Assignment_12_Proxy.proxyyyyyyy.DocumentInterface;

import java.time.LocalDate;

public class Document implements DocumentInterface {
    private String id;
    private String content;
    private LocalDate creationDate;

    public Document(String id, LocalDate creationDate, String content) {
        this.id = id;
        this.creationDate = creationDate;
        this.content = content;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public String getContent(User user) {
        // Implement access control logic here if needed
        return content;
    }
}
