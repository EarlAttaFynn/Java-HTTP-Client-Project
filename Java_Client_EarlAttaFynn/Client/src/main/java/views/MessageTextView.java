package views;

import models.Message;

public class MessageTextView {
    Message msgToDisplay;

    public MessageTextView(Message msgToDisplay) {
        this.msgToDisplay = msgToDisplay;
    }

    @Override public String toString() {
        return String.format("\nSequence: %s\nTimeStamp: %s\nFromId: %s\nToId: %s\nMessage: %s",
                msgToDisplay.getSequence(), msgToDisplay.getTimestamp(), msgToDisplay.getFromid(),
                msgToDisplay.getToid(), msgToDisplay.getMessage());
    } 
}