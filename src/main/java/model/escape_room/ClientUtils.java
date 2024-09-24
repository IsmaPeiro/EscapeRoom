package model.escape_room;

import model.clients.Client;

import java.util.Scanner;

public class ClientUtils {
    public static Client addClient() {
        String name, surname;
        boolean subscribed;
        
        name=Input.readString("Input the name:");
        surname=Input.readString("Input the surname");
        subscribed=Input.readYesNo("Want to subscribe?");
        
        return new Client(name, surname, subscribed);
    }
}
