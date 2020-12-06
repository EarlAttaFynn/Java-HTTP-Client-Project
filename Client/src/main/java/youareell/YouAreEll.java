package youareell;

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
        System.out.println(urlhandler.sorter("/ids", "GET", ""));
//        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    public boolean sorter(String mainurl, String method, String jpayload) throws IOException {
        if (method.equals("GET")) getURLCall(mainurl, jpayload);
        if (method.equals("POST")) postURLCall(mainurl, jpayload);
        if (method.equals("PUT")) putURLCall(mainurl, jpayload);
        return true;
    }


    public String getURLCall(String mainurl, String jpayload) throws IOException {
        if (mainurl.equals("/ids")){
            String response = transactionController.get(mainurl);
            ArrayList<Id> idsList = idCtrl.getIds(response);
            System.out.println(idsList);
        } else if (mainurl.equals("/messages")){
            String response = transactionController.get(mainurl);
            ArrayList<Message> messagesList = msgCtrl.getMessages(response);
            System.out.println(messagesList);
        }
        return "nada";
    }

    public String postURLCall(String mainurl, String jpayload){
        if (mainurl.equals("/ids")){
        } else if (mainurl.equals("/messages")){
        }
        return "nada";
    }

    public String putURLCall(String mainurl, String jpayload){
        if (mainurl.equals("/ids")){
        } else if (mainurl.equals("/messages")){
        }
        return "nada";
    }

}
