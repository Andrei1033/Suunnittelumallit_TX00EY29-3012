package org.example.controller_controller_controller_controller_controller_controller;

import javafx.stage.Stage;
import org.example.client.ChatClient;
import org.example.mediator.ChatMediator;
import org.example.view.ChatView;

public class ChatController {

    private final ChatClient client;
    private final ChatMediator mediator;
    private final ChatView view;

    public ChatController(
            ChatClient client,
            ChatMediator mediator,
            Stage stage
    ) {

        this.client = client;
        this.mediator = mediator;

        // Create the View.
        this.view = new ChatView(
                stage,
                client.getUsername()
        );

        // Connect the View to this Controller.
        view.setSendHandler(this::sendMessage);

        // Connect the Client to this Controller.
        client.setMessageHandler(this::receiveMessage);
    }

    // Called when the user wants to send a message.
    private void sendMessage(
            String recipient,
            String message
    ) {

        if (message == null || message.isBlank()) {
            return;
        }

        if (recipient == null) {
            view.appendMessage(
                    "System: Please select a recipient."
            );
            return;
        }

        // Ask the Mediator to deliver the message.
        boolean sent = mediator.sendMessage(
                client,
                recipient,
                message
        );

        if (sent) {

            // Display the sent message in our own window.
            view.appendMessage(
                    "You -> " + recipient + ": " + message
            );

            view.clearMessageInput();

        } else {

            view.appendMessage(
                    "System: Could not send message. "
                            + "Check the recipient."
            );
        }
    }

    // Called when this client receives a message.
    private void receiveMessage(
            String sender,
            String message
    ) {

        view.appendMessage(
                sender + ": " + message
        );
    }

    // Refresh the recipient list.
    public void refreshRecipients() {
        view.setRecipients(mediator.getUsernames());
    }

    // Display this client's window.
    public void show() {
        view.show();
    }
}
