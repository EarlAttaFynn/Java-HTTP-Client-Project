package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import controllers.IdController;
import controllers.MessageController;
import controllers.TransactionController;
import models.Id;
import models.Message;
import youareell.YouAreEll;

// Simple Shell is a Console view for youareell.YouAreEll.
public class SimpleShell {


    public static void prettyPrint(String output) {
        // yep, make an effort to format things nicely, eh?
        System.out.println(output);
    }
    public static void main(String[] args) throws java.io.IOException {

        YouAreEll webber = new YouAreEll(new MessageController(), new IdController(), new TransactionController());
        
        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            System.out.println("cmd? ");
            commandLine = console.readLine();

            //input parsed into array of strings(command and arguments)
            String[] commands = commandLine.split(" ");
            List<String> list = new ArrayList<String>();

            //if the user entered a return, just loop again
            if (commandLine.equals(""))
                System.out.println("cmd? ");
                commandLine = console.readLine();
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                list.add(commands[i]);

            }
            System.out.print(list); //***check to see if list was added correctly***
            history.addAll(list);
            try {
                //display history of shell with index
                if (list.get(list.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                // Specific Commands.

                // ids
                if (list.contains("ids") && list.contains("GET")) {
                    String results = webber.getURLCall("/ids",  "");
                    SimpleShell.prettyPrint(results);
                    continue;
                }

                if (list.contains("ids") && list.contains("POST")) {
                    System.out.println("Name?");
                    String name = console.readLine();

                    System.out.println("Github?");
                    String github = console.readLine();

                    Id obj = new Id();
                    obj.setName(name);
                    obj.setGithub(github);
                    obj.setUserid("");

                    Gson gson = new Gson();
                    String jpayload = gson.toJson(obj);

                    String results = webber.postURLCall("/ids",  jpayload);
                    SimpleShell.prettyPrint(results);
                    continue;
                }

//                if (list.contains("ids") && list.contains("PUT")) {
//                    System.out.println("Name?");
//                    String name = console.readLine();
//
//                    System.out.println("Github?");
//                    String github = console.readLine();
//
//                    Id obj = new Id();
//                    obj.setName(name);
//                    obj.setGithub(github);
//                    obj.setUserid("");
//
//                    Gson gson = new Gson();
//                    String jpayload = gson.toJson(obj);
//
//                    String results = webber.putURLCall("/ids",  jpayload);
//                    SimpleShell.prettyPrint(results);
//                    continue;
//                }

                // messages
                if (list.contains("messages") && list.contains("GET")) {
                    String results = webber.getURLCall("/messages",  "");
                    SimpleShell.prettyPrint(results);
                    continue;
                }

                if (list.contains("messages") && list.contains("POST")) {
                    System.out.println("FromId?");
                    String fromId = console.readLine();

                    System.out.println("ToId?");
                    String toId = console.readLine();

                    System.out.println("Message?");
                    String message = console.readLine();

                    Message obj = new Message();
                    obj.setFromid(fromId);
                    obj.setToid(toId);
                    obj.setMessage(message);

                    Gson gson = new Gson();
                    String jpayload = gson.toJson(obj);
                    if (toId.isEmpty()) {
                        String results = webber.postURLCall("/ids/\"\"/messages",  jpayload);
                        SimpleShell.prettyPrint(results);
                        continue;
                    }
                    String results = webber.postURLCall("/ids/" +toId+ "/messages",  jpayload);
                    SimpleShell.prettyPrint(results);
                    continue;
                }

//                if (list.contains("messages") && list.contains("PUT")) {
//                    System.out.println("FromId?");
//                    String fromId = console.readLine();
//
//                    System.out.println("ToId?");
//                    String toId = console.readLine();
//
//                    System.out.println("Message?");
//                    String message = console.readLine();
//
//                    Message obj = new Message();
//                    obj.setFromid(fromId);
//                    obj.setToid(toId);
//                    obj.setMessage(message);
//
//                    Gson gson = new Gson();
//                    String jpayload = gson.toJson(obj);
//                    if (toId.isEmpty()) {
//                        String results = webber.putURLCall("/ids/\"\"/messages",  jpayload);
//                        SimpleShell.prettyPrint(results);
//                        continue;
//                    }
//                    String results = webber.putURLCall("/ids/" +toId+ "/messages",  jpayload);
//                    SimpleShell.prettyPrint(results);
//                    continue;
//                }

                //!! command returns the last command in history
                if (list.get(list.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (list.get(list.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(list.get(list.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(list);
                }

                // wait, wait, what curiousness is this?
                Process process = pb.start();

                //obtain the input stream
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                //read output of the process
                String line;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
                br.close();


            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (IOException e) {
                System.out.println("Input Error, Please try again!");
            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }


    }

}