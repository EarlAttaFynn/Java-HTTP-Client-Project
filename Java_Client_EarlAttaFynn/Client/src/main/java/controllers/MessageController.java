package controllers;

import java.util.ArrayList;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;

public class MessageController {

    private HashSet<Message> messagesSeen;
    // why a HashSet??


    public ArrayList<Message> getMessages(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Message> messageList = new ArrayList<>();
        try {
            messageList = objectMapper.readValue(response, new TypeReference<ArrayList<Message>>() {
            });
        } catch (JsonProcessingException e) {
            if (e.getMessage().contains("Cannot deserialize value of type")) {
                Message msg = objectMapper.readValue(response, Message.class);
                messageList = new ArrayList<Message>();
                messageList.add(msg);
                return messageList;
            }
        }
        return messageList;
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }

    public Message getMessageForSequence(String seq) {
        return null;
    }

    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }

}