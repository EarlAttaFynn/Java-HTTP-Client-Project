package controllers;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;

public class IdController {
    Id myId;

    public ArrayList<Id> getIds(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Id> idList = objectMapper.readValue(response, new TypeReference<ArrayList<Id>>(){});
        return idList; //returns an ID object to IdTextView
    }

    public Id postId(Id id) {
        return null;
    }

    public Id putId(Id id) {
        return null;
    }
 
}