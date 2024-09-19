package model.escape_room;

import java.util.Scanner;

public class EscapeRoomManagement {
    public void init() {
        
        EscapeRoom escape = new EscapeRoom();
        
        boolean exit = false;
        
        do {
            switch (menu()) {
                case 1 -> addRoom(escape);
                case 2 -> listRooms(escape);
                case 3 -> addClue(escape);
                case 4 -> addDecoration(escape);
                case 0 -> {
                    System.out.println("thanks.");
                    exit = true;
                }
            }
        } while (!exit);
    }
    
    
    public byte menu() {
        Scanner sc = new Scanner(System.in);
        byte option = -1;
        final byte MINIMUM = 0;
        final byte MAXIMUM = 3;
        
        do {
            System.out.println("\nMENú PRINCIPAL");
            System.out.println("1. Add Room.");
            System.out.println("2. List Rooms.");
            System.out.println("3. Add Clue to Room.");
            System.out.println("3. Add Decoration to Room.");
            System.out.println("0. Exit.\n");
            if (sc.hasNextByte()) option = sc.nextByte();
            sc.nextLine();
            if (option < MINIMUM || option > MAXIMUM) {
                System.out.println("Chose a valid option.");
            }
        } while (option < MINIMUM || option > MAXIMUM);
        return option;
    }
    
    public void addRoom (EscapeRoom scape) {
        scape.addRoom(RoomUtils.addRoom());
    }
    
    public void listRooms(EscapeRoom scape) {
        scape.showAllRooms();
    }
    
    private void addClue(EscapeRoom escape) {
        escape.addClueToRoom();
    }
    
    private void addDecoration(EscapeRoom escape) {
        escape.addDecorationToRoom();
    }
}