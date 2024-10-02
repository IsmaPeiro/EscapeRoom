package model.escape_room;

import model.clients.Client;
import model.rooms.Room;

public class ClientUtils {
    public static Client inputClient() {
        String name, surname;
        boolean subscribed;
        
        name = Input.readString("Input the name:");
        surname = Input.readString("Input the surname");
        subscribed = Input.readYesNo("Want to subscribe?");
        
        return new Client(name, surname, subscribed);
    }
    
    public static void printCertificate(Client client, Room room) {
        int points = calculatePoints(room);
        System.out.println("-- Escape Certificate --");
        System.out.println("¡¡¡Congratulations!!!");
        System.out.println("You have escaped from " + room.getName() + " room.");
        System.out.println("Level: "+ room.getDifficulty());
        System.out.println("Total clues: "+ room.getClues().size());
        System.out.println("Client id: " + client.getId() + " --- Total score: " + points);
        System.out.println("For each point you will get a 1% discount, handing this certificate.");
    }
    
    private static int calculatePoints(Room room) {
        int points = 0;
        switch (room.getDifficulty()) {
            case EASY -> points = 5;
            case MEDIUM -> points = 10;
            case HARD -> points = 15;
        }
        
        points -= room.getClues().size();
        
        if (points <= 0) points = 1;
        
        return points;
    }
}

