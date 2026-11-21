package org.example.client;

import java.util.function.BiConsumer;

public class ChatClient {

    private final String username;

    private BiConsumer<String, String> messageHandler =
            (sender, message) -> {};

    public ChatClient(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setMessageHandler(
            BiConsumer<String, String> messageHandler
    ) {
        this.messageHandler = messageHandler;
    }

    public void receiveMessage(String sender, String message) {

        messageHandler.accept(sender, message);
    }
}