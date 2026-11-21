package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.client.ChatClient;
import org.example.controller_controller_controller_controller_controller_controller.ChatController;
import org.example.mediator.ChatMediator;
import org.example.mediator.ConcreteChatMediator;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) {

        // 1. Create ONE shared Mediator.
        ChatMediator mediator =
                new ConcreteChatMediator();

        // 2. Create three clients.
        List<ChatClient> clients = new ArrayList<>();

        clients.add(new ChatClient("Alice"));
        clients.add(new ChatClient("Bob"));
        clients.add(new ChatClient("Charlie"));

        // 3. Register all clients.
        for (ChatClient client : clients) {
            mediator.registerClient(client);
        }

        // 4. Create a controller for each client.
        List<ChatController> controllers =
                new ArrayList<>();

        for (int i = 0; i < clients.size(); i++) {

            Stage stage;

            if (i == 0) {
                stage = primaryStage;
            } else {
                stage = new Stage();
            }

            ChatController controller =
                    new ChatController(
                            clients.get(i),
                            mediator,
                            stage
                    );

            controllers.add(controller);
        }

        // 5. Update recipient lists.
        for (ChatController controller : controllers) {
            controller.refreshRecipients();
        }

        // 6. Display all windows.
        for (ChatController controller : controllers) {
            controller.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
