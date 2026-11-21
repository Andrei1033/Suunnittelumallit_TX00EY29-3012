package org.example.mediator;

import org.example.client.ChatClient;

import java.util.Collection;

public interface ChatMediator {

    void registerClient(ChatClient client);

    boolean sendMessage(
            ChatClient sender,
            String recipientUsername,
            String message
    );

    Collection<String> getUsernames();
}