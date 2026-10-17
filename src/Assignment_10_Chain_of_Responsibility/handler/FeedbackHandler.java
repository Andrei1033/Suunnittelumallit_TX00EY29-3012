package Assignment_10_Chain_of_Responsibility.handler;

import Assignment_10_Chain_of_Responsibility.Message;

public abstract class FeedbackHandler {
    private FeedbackHandler nextHandler;

    public FeedbackHandler setNext(FeedbackHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    public abstract String handle(Message message);

    protected String handleNext(Message message) {
        if (nextHandler != null) {
            return nextHandler.handle(message);
        }

        return "No handler found for message type: "
                + message.getMessageType();
    }
}
