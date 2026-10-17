package Assignment_10_Chain_of_Responsibility.handler;

import Assignment_10_Chain_of_Responsibility.Message;
import Assignment_10_Chain_of_Responsibility.MessageType;

public class ContactRequestHandler extends  FeedbackHandler{
    @Override
    public String handle(Message message) {

        if (message.getMessageType()
                == MessageType.CONTACT_REQUEST) {

            return "Contact request received from "
                    + message.getSenderEmail()
                    + ". Message: "
                    + message.getContent()
                    + ". Forwarded to the customer service department.";
        }

        return handleNext(message);
    }
}
