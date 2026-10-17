package Assignment_10_Chain_of_Responsibility.handler;

import Assignment_10_Chain_of_Responsibility.Message;
import Assignment_10_Chain_of_Responsibility.MessageType;

public class CompensationHandler extends FeedbackHandler{
    @Override
    public String handle(Message message) {

        if (message.getMessageType()
                == MessageType.COMPENSATION_CLAIM) {

            return "Compensation claim received from "
                    + message.getSenderEmail()
                    + ". Claim reviewed: "
                    + message.getContent()
                    + ". Status: Pending review.";
        }

        return handleNext(message);
    }
}
