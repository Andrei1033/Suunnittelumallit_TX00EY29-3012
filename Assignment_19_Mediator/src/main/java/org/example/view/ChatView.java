package org.example.view;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.function.BiConsumer;

public class ChatView {

    private final Stage stage;

    private final TextArea chatHistory;
    private final TextField messageInput;
    private final ComboBox<String> recipientSelector;

    private BiConsumer<String, String> sendHandler;

    public ChatView(Stage stage, String username) {

        this.stage = stage;

        stage.setTitle("Chat - " + username);

        // Chat history
        chatHistory = new TextArea();
        chatHistory.setEditable(false);
        chatHistory.setWrapText(true);
        chatHistory.setPromptText(
                "Your messages will appear here..."
        );
        chatHistory.getStyleClass().add("chat-history");

        // Message input
        messageInput = new TextField();
        messageInput.setPromptText("Type your message...");
        messageInput.getStyleClass().add("message-input");

        // Recipient selector
        recipientSelector = new ComboBox<>();
        recipientSelector.setPromptText("Select recipient");
        recipientSelector.setMaxWidth(Double.MAX_VALUE);
        recipientSelector.getStyleClass().add("recipient-selector");

        // Send button
        Button sendButton = new Button("Send");
        sendButton.getStyleClass().add("send-button");

        // Title
        Label titleLabel = new Label("Chat - " + username);
        titleLabel.getStyleClass().add("chat-title");

        // Chat history label
        Label historyLabel = new Label("Message history");
        historyLabel.getStyleClass().add("section-label");

        // Recipient row
        Label recipientLabel = new Label("Recipient:");
        recipientLabel.getStyleClass().add("section-label");

        HBox recipientRow = new HBox(
                10,
                recipientLabel,
                recipientSelector
        );

        recipientRow.getStyleClass().add("recipient-row");

        HBox.setHgrow(
                recipientSelector,
                Priority.ALWAYS
        );

        // Message input row
        HBox messageRow = new HBox(
                10,
                messageInput,
                sendButton
        );

        messageRow.getStyleClass().add("message-row");

        HBox.setHgrow(
                messageInput,
                Priority.ALWAYS
        );

        // Main layout
        VBox layout = new VBox(
                12,
                titleLabel,
                historyLabel,
                chatHistory,
                recipientRow,
                messageRow
        );

        layout.getStyleClass().add("chat-root");

        VBox.setVgrow(
                chatHistory,
                Priority.ALWAYS
        );

        // Button actions
        sendButton.setOnAction(event -> sendMessage());

        messageInput.setOnAction(event -> sendMessage());

        // Create scene
        Scene scene = new Scene(layout, 450, 450);

        // Load CSS
        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles/chat.css")
                        .toExternalForm()
        );

        stage.setScene(scene);
    }

    public void setSendHandler(
            BiConsumer<String, String> sendHandler
    ) {
        this.sendHandler = sendHandler;
    }

    public void setRecipients(Collection<String> usernames) {
        recipientSelector.getItems().setAll(usernames);
    }

    private void sendMessage() {

        if (sendHandler == null) {
            return;
        }

        String recipient = recipientSelector.getValue();
        String message = messageInput.getText().trim();

        sendHandler.accept(recipient, message);
    }

    public void appendMessage(String message) {
        chatHistory.appendText(message + "\n");
    }

    public void clearMessageInput() {
        messageInput.clear();
    }

    public void show() {
        stage.show();
    }
}
