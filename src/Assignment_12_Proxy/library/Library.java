package Assignment_12_Proxy.library;

import Assignment_12_Proxy.model.Document;
import Assignment_12_Proxy.proxy.DocumentInterface;
import Assignment_12_Proxy.proxy.DocumentProxy;
import Assignment_12_Proxy.service.AccessControlService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Library {

    private Map<String, DocumentInterface> documents;

    public Library() {
        documents = new HashMap<>();
    }

    // Add an unprotected document
    public void addUnprotectedDocument(
            String id,
            LocalDate creationDate,
            String content) {

        Document document =
                new Document(id, creationDate, content);

        documents.put(id, document);
    }

    // Add a protected document
    public void addProtectedDocument(
            String id,
            LocalDate creationDate,
            String content) {

        Document document =
                new Document(id, creationDate, content);

        DocumentProxy proxy =
                new DocumentProxy(document);

        documents.put(id, proxy);
    }

    // Find a document
    public DocumentInterface getDocument(String id) {

        return documents.get(id);
    }

    // Give permission to a user
    public void grantAccess(String documentId, String username) {

        AccessControlService.getInstance()
                .grantAccess(documentId, username);
    }
}