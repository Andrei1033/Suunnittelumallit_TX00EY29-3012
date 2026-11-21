package org.example.mediator;

import org.example.client.ChatClient;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConcreteChatMediator implements ChatMediator {

    private final Map<String, ChatClient> clients =
            new LinkedHashMap<>();

    @Override
    public void registerClient(ChatClient client) {

        String username = client.getUsername();

        if (clients.containsKey(username)) {
            throw new IllegalArgumentException(
                    "Username already exists: " + username
            );
        }

        clients.put(username, client);
    }

    @Override
    public boolean sendMessage(
            ChatClient sender,
            String recipientUsername,
            String message
    ) {

        // Check that the sender is registered.
        if (clients.get(sender.getUsername()) != sender) {
            return false;
        }

        // Find the recipient.
        ChatClient recipient = clients.get(recipientUsername);

        if (recipient == null) {
            return false;
        }

        // Deliver the message to the recipient.
        recipient.receiveMessage(
                sender.getUsername(),
                message
        );

        return true;
    }

    @Override
    public Collection<String> getUsernames() {
        return clients.keySet();
    }
}