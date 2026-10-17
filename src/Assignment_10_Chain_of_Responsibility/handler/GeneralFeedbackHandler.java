package Assignment_10_Chain_of_Responsibility.handler;

import Assignment_10_Chain_of_Responsibility.Message;
import Assignment_10_Chain_of_Responsibility.MessageType;

public class GeneralFeedbackHandler extends FeedbackHandler{
    @Override
    public String handle(Message message) {

        if (message.getMessageType()
                == MessageType.GENERAL_FEEDBACK) {

            return "General feedback received from "
                    + message.getSenderEmail()
                    + ". Feedback: "
                    + message.getContent()
                    + ". Response: Thank you for your feedback!";
        }

        return handleNext(message);
    }
}
