package youareell;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.*;
import models.Id;
import models.Message;

import java.io.IOException;
import java.util.ArrayList;

public class YouAreEll {

    private MessageController msgCtrl;
    private IdController idCtrl;
    private TransactionController transactionController;

    public YouAreEll (MessageController m, IdController j, TransactionController c) {
        // used j because i seems awkward
        this.msgCtrl = m;
        this.idCtrl = j;
        this.transactionController = c;
    }

    public static void main(String[] args) throws IOException {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(new MessageController(), new IdController(), new TransactionController());
    }

    public String getURLCall(String urlExtension, String jpayload) throws IOException {
        if (urlExtension.equals("/ids")){
            String response = transactionController.get(urlExtension);
            ArrayList<Id> idsList = idCtrl.getIds(response);
            System.out.println(idsList);
        } else if (urlExtension.equals("/messages")){
            String response = transactionController.get(urlExtension);
            ArrayList<Message> messagesList = msgCtrl.getMessages(response);
            System.out.println(messagesList);
        }
        return "Code: 200";
    }

    public String postURLCall(String urlExtension, String jpayload) throws IOException {
        if (urlExtension.contains("/ids") && !urlExtension.contains("messages")){
            String response = transactionController.post(urlExtension, jpayload);
            ArrayList<Id> idsList = idCtrl.getIds(response);
            System.out.println(idsList);
        } else if (urlExtension.contains("ids") && urlExtension.contains("messages")){
            String response = transactionController.post(urlExtension, jpayload);
            ArrayList<Message> messagesList = msgCtrl.getMessages(response);
            System.out.println(messagesList);
        }
        return "Code: 200";
    }

//    public String putURLCall(String mainurl, String jpayload){
//        if (mainurl.equals("/ids")){
//        } else if (mainurl.equals("/messages")){
//        }
//        return "nada";
//    }

}
