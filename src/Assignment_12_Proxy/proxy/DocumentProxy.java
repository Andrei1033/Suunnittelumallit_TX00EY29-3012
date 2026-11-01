package Assignment_12_Proxy.proxy;

import Assignment_12_Proxy.model.AccessDeniedException;
import Assignment_12_Proxy.model.Document;
import Assignment_12_Proxy.model.User;
import Assignment_12_Proxy.service.AccessControlService;

import java.time.LocalDate;

public class DocumentProxy implements DocumentInterface {
    private Document document;
    private AccessControlService accessControlService;


    public DocumentProxy(Document document) {
        this.document = document;
        this.accessControlService = AccessControlService.getInstance();
    }

    @Override
    public String getId() {
        return document.getId();
    }

    @Override
    public LocalDate getCreationDate() {
        return document.getCreationDate();
    }

    @Override
    public String getContent(User user) {
        boolean allowed = accessControlService.isAllowed(
                document.getId(),
                user.getUsername()
        );

        if (allowed) {

            return document.getContent(user);

        }
        else {
            throw new AccessDeniedException(
                    "User " + user.getUsername()
                            + " is not allowed to access document "
                            + document.getId()
            );
        }
    }
}
