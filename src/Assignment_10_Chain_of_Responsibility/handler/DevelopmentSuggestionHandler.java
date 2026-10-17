package Assignment_10_Chain_of_Responsibility.handler;

import Assignment_10_Chain_of_Responsibility.Message;
import Assignment_10_Chain_of_Responsibility.MessageType;

public class DevelopmentSuggestionHandler extends FeedbackHandler{
    @Override
    public String handle(Message message) {

        if (message.getMessageType()
                == MessageType.DEVELOPMENT_SUGGESTION) {

            return "Development suggestion received from "
                    + message.getSenderEmail()
                    + ". Suggestion: "
                    + message.getContent()
                    + ". Status: Logged with medium priority.";
        }

        return handleNext(message);
    }
}
