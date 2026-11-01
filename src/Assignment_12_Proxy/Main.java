package Assignment_12_Proxy;

import Assignment_12_Proxy.library.Library;
import Assignment_12_Proxy.model.AccessDeniedException;
import Assignment_12_Proxy.model.User;
import Assignment_12_Proxy.proxy.DocumentInterface;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        // 1. Create users
        User alice = new User("Alice");
        User bob = new User("Bob");

        // 2. Create library
        Library library = new Library();

        // 3. Add unprotected document
        library.addUnprotectedDocument(
                "DOC001",
                LocalDate.of(2025, 1, 10),
                "This is public information."
        );

        // 4. Add protected documents
        library.addProtectedDocument(
                "DOC002",
                LocalDate.of(2025, 2, 15),
                "This is confidential company information."
        );

        library.addProtectedDocument(
                "DOC003",
                LocalDate.of(2025, 3, 20),
                "This is private meeting information."
        );

        // 5. Give permissions
        library.grantAccess("DOC002", "Alice");
        library.grantAccess("DOC003", "Bob");

        // 6. Test unprotected document
        System.out.println("=== Public document ===");

        DocumentInterface publicDocument =
                library.getDocument("DOC001");

        System.out.println(
                publicDocument.getContent(alice)
        );

        System.out.println(
                "Creation date: "
                        + publicDocument.getCreationDate()
        );

        // 7. Test protected document with permission
        System.out.println("\n=== Alice accesses DOC002 ===");

        try {

            DocumentInterface document =
                    library.getDocument("DOC002");

            System.out.println(
                    document.getContent(alice)
            );

        } catch (AccessDeniedException e) {

            System.out.println(e.getMessage());
        }

        // 8. Test protected document without permission
        System.out.println("\n=== Bob accesses DOC002 ===");

        try {

            DocumentInterface document =
                    library.getDocument("DOC002");

            System.out.println(
                    document.getContent(bob)
            );

        } catch (AccessDeniedException e) {

            System.out.println(e.getMessage());
        }

        // 9. Test Bob accessing DOC003
        System.out.println("\n=== Bob accesses DOC003 ===");

        try {

            DocumentInterface document =
                    library.getDocument("DOC003");

            System.out.println(
                    document.getContent(bob)
            );

        } catch (AccessDeniedException e) {

            System.out.println(e.getMessage());
        }

        // 10. Test public creation date
        System.out.println("\n=== Public creation date ===");

        DocumentInterface protectedDocument =
                library.getDocument("DOC002");

        System.out.println(
                protectedDocument.getCreationDate()
        );
    }
}