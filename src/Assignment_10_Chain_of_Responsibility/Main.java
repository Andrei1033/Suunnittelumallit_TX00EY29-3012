package Assignment_10_Chain_of_Responsibility;

import Assignment_10_Chain_of_Responsibility.handler.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Create the handlers
        FeedbackHandler compensationHandler =
                new CompensationHandler();

        FeedbackHandler contactHandler =
                new ContactRequestHandler();

        FeedbackHandler developmentHandler =
                new DevelopmentSuggestionHandler();

        FeedbackHandler generalHandler =
                new GeneralFeedbackHandler();

        // Connect the handlers into a chain
        compensationHandler
                .setNext(contactHandler)
                .setNext(developmentHandler)
                .setNext(generalHandler);

        // Create sample customer messages
        List<Message> messages = List.of(
                new Message(
                        MessageType.COMPENSATION_CLAIM,
                        "My order arrived damaged.",
                        "anna@example.com"
                ),
                new Message(
                        MessageType.CONTACT_REQUEST,
                        "Please ask sales to contact me.",
                        "john@example.com"
                ),
                new Message(
                        MessageType.DEVELOPMENT_SUGGESTION,
                        "Please add a dark mode.",
                        "maria@example.com"
                ),
                new Message(
                        MessageType.GENERAL_FEEDBACK,
                        "I enjoyed using your service.",
                        "alex@example.com"
                )
        );

        // Send every message to the beginning of the chain
        for (Message message : messages) {

            String result =
                    compensationHandler.handle(message);

            System.out.println(result);
            System.out.println("------------------------------");
        }
    }
}
