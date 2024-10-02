package model.escape_room;

import java.util.Scanner;

public class EscapeIU {
    public void init(String database) {
        
        Inventory inventory = new Inventory(database);
        RoomManagement rm=new RoomManagement(database);
        ClueManagement cm=new ClueManagement(database);
        DecorationManagement dm=new DecorationManagement(database);
        ClientManagement clm=new ClientManagement(database);
        TicketManagement tm = new TicketManagement(database);
        
        boolean exit = false;
        
        do {
            switch (menu()) {
                case 1 -> addRoom(rm);
                case 2 -> listRooms(rm);
                case 3 -> addClue(rm, cm);
                case 4 -> addDecoration(rm, dm);
                case 5 -> addClient(clm);
                case 6 -> createTicket(rm, tm, clm);
                case 7 -> listClients(clm);
                case 8 -> listTickets(rm, tm);
                case 9 -> RemoveRoom(rm);
                case 10 -> RemoveDecorationRoom(rm, dm);
                case 11 -> RemoveClueRoom(rm, cm);
                case 12 -> showInventory(inventory);
                case 13 -> subscribeClientNewsletter(clm);
                case 14 -> unsubscribeClientNewsletter(clm);
                case 15 -> printCertificate(rm, clm);
                case 16 -> buyClue(cm);
                case 17 -> buyDecoration(dm);
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
        final byte MAXIMUM = 17;
        
        do {
            System.out.println("\nMAIN MENU");
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
            System.out.println("11. Remove Clue from Room.");
            System.out.println("12. Show Inventory.");
            System.out.println("13. Subscribe Client.");
            System.out.println("14. Unsubscribe Client.");
            System.out.println("15. Print certificate.");
            System.out.println("16. Buy Clue.");
            System.out.println("17. Buy Decoration.");
            System.out.println("0. Exit.\n");
            if (sc.hasNextByte()) option = sc.nextByte();
            sc.nextLine();
            if (option < MINIMUM || option > MAXIMUM) {
                System.out.println("Chose a valid option.");
            }
        } while (option < MINIMUM || option > MAXIMUM);
        return option;
    }
    
    private void addRoom(RoomManagement rm) {
        rm.addRoom();
    }
    
    private void listRooms(RoomManagement rm) {
        rm.showAllRooms();
    }
    
    private void addClue(RoomManagement rm, ClueManagement cm) {
        cm.addClueToRoom(rm);
    }
    
    private void addDecoration(RoomManagement rm, DecorationManagement dm) {
        dm.addDecorationToRoom(rm);
    }
    
    private void addClient(ClientManagement clm) {
        clm.addClient();
    }
    
    private void createTicket(RoomManagement rm, TicketManagement tm, ClientManagement clm) {
        tm.createTicket(rm, clm);
    }
    
    private void listClients(ClientManagement clm) {
        clm.listClients();
    }
    
    private void listTickets(RoomManagement rm, TicketManagement tm) {
        tm.listTickets();
    }
    
    private void RemoveRoom(RoomManagement rm) {
        rm.removeRoom ();
    }
    
    private void RemoveDecorationRoom(RoomManagement rm, DecorationManagement dm) {
        dm.removeDecorationRoom (rm);
    }
    
    private void RemoveClueRoom(RoomManagement rm, ClueManagement cm) {
        cm.removeClueRoom (rm);
    }
    
    private void showInventory(Inventory escape) {
        escape.showInventory();
    }
    
    private void unsubscribeClientNewsletter(ClientManagement clm){
        clm.unsubscribeClient();
    }
    
    private void subscribeClientNewsletter(ClientManagement clm) {
        clm.subscribeClient();
    }
    
    private void printCertificate(RoomManagement rm, ClientManagement clm) {
        clm.printCertificate(rm);
    }
    
    private void buyDecoration(DecorationManagement dm) {
        dm.newDecoration();
    }
    
    private void buyClue(ClueManagement cm) {
        cm.newClue();
    }
}