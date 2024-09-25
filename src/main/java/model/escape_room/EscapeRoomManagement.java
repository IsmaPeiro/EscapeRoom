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
                case 5 -> addClient(escape);
                case 6 -> createTicket(escape);
                case 7 -> listClients(escape);
                case 8 -> listTickets(escape);
                case 9 -> RemoveRoom(escape);
                case 10 -> RemoveDecorationRoom(escape);
                case 11 -> showInventory(escape);
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
        final byte MAXIMUM = 11;
        
        do {
            System.out.println("\nMENú PRINCIPAL");
            System.out.println("1. Add Room.");
            System.out.println("2. List Rooms.");
            System.out.println("3. Add Clue to Room.");
            System.out.println("4. Add Decoration to Room.");
            System.out.println("5. Add Client.");
            System.out.println("6. Create Ticket.");
            System.out.println("7. List Clients.");
            System.out.println("8. List Tickets.");
            System.out.println("9. Remove Room.");
            System.out.println("10. Remove Decoration from Room.");
            System.out.println("11. Show Inventory.");
            System.out.println("0. Exit.\n");
            if (sc.hasNextByte()) option = sc.nextByte();
            sc.nextLine();
            if (option < MINIMUM || option > MAXIMUM) {
                System.out.println("Chose a valid option.");
            }
        } while (option < MINIMUM || option > MAXIMUM);
        return option;
    }
    
    public void addRoom(EscapeRoom escape) {
        escape.addRoom(RoomUtils.addRoom());
    }
    
    public void listRooms(EscapeRoom escape) {
        escape.showAllRooms();
    }
    
    private void addClue(EscapeRoom escape) {
        escape.addClueToRoom();
    }
    
    private void addDecoration(EscapeRoom escape) {
        escape.addDecorationToRoom();
    }
    
    private void addClient(EscapeRoom escape) {
        escape.addClient(ClientUtils.addClient());
    }
    
    private void createTicket(EscapeRoom escape) {
        escape.createTicket(TicketUtils.createTicket());
    }
    
    private void listClients(EscapeRoom escape) {
        escape.listClients();
    }
    
    private void listTickets(EscapeRoom escape) {
        escape.listTickets();
    }
    
    private void RemoveRoom(EscapeRoom escape) {
        escape.removeRoom (RoomUtils.removeRoom());
    }
    
    private void RemoveDecorationRoom(EscapeRoom escape) {
        escape.removeDecorationRoom ();
    }
    
    private void showInventory(EscapeRoom escape) {
        escape.showInventory();
    }
}