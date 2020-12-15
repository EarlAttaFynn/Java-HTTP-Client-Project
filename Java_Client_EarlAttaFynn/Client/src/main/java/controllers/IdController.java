package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;

public class IdController {
    Id myId;

    public ArrayList<Id> getIds(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Id> idList = new ArrayList<>();
        try {
            idList = objectMapper.readValue(response, new TypeReference<ArrayList<Id>>() {
            });
            return idList;
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
            if (e.getMessage().contains("Cannot deserialize value of type")) {
                Id id = objectMapper.readValue(response, Id.class);
                ArrayList<Id> idlist = new ArrayList<Id>();
                idlist.add(id);
                return idlist;
            }
        }
        //returns an ID object to IdTextView
    return idList;
    }



    public Id postId(Id id) {
        return null;
    }

    public Id putId(Id id) {
        return null;
    }
 
}