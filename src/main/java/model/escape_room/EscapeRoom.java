package model.escape_room;

import dao.*;
import dao.mysql.*;
import model.clients.Client;
import model.clients.Ticket;
import model.clues.Clue;
import model.decorations.Decoration;
import model.rooms.Room;
import observer.ClientObservable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EscapeRoom {
    private List<Room> rooms = new ArrayList<>();
    
    public void addRoom(Room room) {
        //rooms.add(room);
        Connection conn = null;
        
        try {
            conn = MySQLUtils.getConn();
            RoomDAO dao = new MySQLRoomDAO(conn);
            dao.create(room);
            ClientObservable clientObservable = new ClientObservable();
            clientObservable.notifyClients(room);
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    public void showAllRooms() {
        // rooms.forEach(System.out::println);
        Connection conn = null;
        float total = 0;
        try {
            conn = MySQLUtils.getConn();
            RoomDAO dao = new MySQLRoomDAO(conn);
            List<Room> rooms = dao.readAll();
            rooms.forEach(r -> System.out.println(r + "Value of Room: " + RoomUtils.calculateValue(r) + "\n"));
            total = (float) rooms.stream().mapToDouble(RoomUtils::calculateValue).sum();
            System.out.println("Total value of rooms: " + total);
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    public void addClueToRoom() {
        Room room;
        Clue clue;
        room = RoomUtils.selectRoom();
        clue = ClueUtils.addClue(room);
        if (clue != null) {
            clue.setIdRoom(room.getId());
            
            Connection conn = null;
            
            try {
                conn = MySQLUtils.getConn();
                ClueDAO dao = new MySQLClueDAO(conn);
                dao.update(clue);
            } catch (DAOException | SQLException e) {
                System.out.println(e);
            } finally {
                MySQLUtils.closeConn(conn);
            }
        }
    }
    
    public void addDecorationToRoom() {
        Room room;
        Decoration decoration;
        room = RoomUtils.selectRoom();
        decoration = DecorationUtils.addDecoration(room);
        if (decoration != null) {
            decoration.setIdRoom(room.getId());
            
            Connection conn = null;
            
            try {
                conn = MySQLUtils.getConn();
                DecorationDAO dao = new MySQLDecorationDAO(conn);
                dao.update(decoration);
            } catch (DAOException | SQLException e) {
                System.out.println(e);
            } finally {
                MySQLUtils.closeConn(conn);
            }
        }
    }
    
    public void removeClueRoom() {
        Room room;
        Clue clue;
        room = RoomUtils.selectRoom();
        clue = ClueUtils.removeClue(room);
        if (clue != null) {
            Connection conn = null;
            
            try {
                conn = MySQLUtils.getConn();
                ClueDAO dao = new MySQLClueDAO(conn);
                dao.setRommToNull(clue);
            } catch (DAOException | SQLException e) {
                System.out.println(e);
            } finally {
                MySQLUtils.closeConn(conn);
            }
        }
    }
    
    public void removeDecorationRoom() {
        Room room;
        Decoration decoration;
        room = RoomUtils.selectRoom();
        decoration = DecorationUtils.removeDecoration(room);
        if (decoration != null) {
            Connection conn = null;
            
            try {
                conn = MySQLUtils.getConn();
                DecorationDAO dao = new MySQLDecorationDAO(conn);
                dao.setRommToNull(decoration);
            } catch (DAOException | SQLException e) {
                System.out.println(e);
            } finally {
                MySQLUtils.closeConn(conn);
            }
        }
    }
    
    public void addClient(Client client) {
        Connection conn = null;
        
        try {
            conn = MySQLUtils.getConn();
            ClientDAO dao = new MySQLClientDAO(conn);
            dao.create(client);
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    public void listClients() {
        Connection conn = null;
        
        try {
            conn = MySQLUtils.getConn();
            ClientDAO dao = new MySQLClientDAO(conn);
            List<Client> clients = dao.readAll();
            clients.forEach(System.out::println);
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    public void createTicket(Ticket ticket) {
        Connection conn = null;
        
        try {
            conn = MySQLUtils.getConn();
            TicketDAO dao = new MySQLTcketDAO(conn);
            dao.create(ticket);
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    public void listTickets() {
        Connection conn = null;
        try {
            conn = MySQLUtils.getConn();
            TicketDAO dao = new MySQLTcketDAO(conn);
            List<Ticket> tickets = dao.readAll();
            tickets.forEach(System.out::println);
            
            System.out.println("Total value of tickets: " +
                    tickets.stream().mapToDouble(Ticket::getValue).sum());
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    public void removeRoom(Room room) {
        if (room != null) {
            Connection conn = null;
            
            try {
                conn = MySQLUtils.getConn();
                RoomDAO dao = new MySQLRoomDAO(conn);
                dao.delete(room);
            } catch (DAOException | SQLException e) {
                System.out.println(e);
            } finally {
                MySQLUtils.closeConn(conn);
            }
        }
    }
    
    public void subscribeClient() {
        Client client = null;
        while (client == null) {
            String message = "Introduce the id of the client you want to subscribe:";
            int id = Input.readInt(message);
            client = ClientUtils.searchClient(id);
            if (client == null || client.isSubscribed()) {
                System.out.println("Client doesn't exists or is already subscribed.");
            } else {
                ClientUtils.subscribe(client);
                System.out.println("Client subscribed.");
            }
            
        }
    }
    
    public void unsubscribeClient() {
        Client client = null;
        while (client == null) {
            String message = "Introduce the id of the client you want to unsubscribe:";
            int id = Input.readInt(message);
            client = ClientUtils.searchClient(id);
            if (client == null || !client.isSubscribed()) {
                System.out.println("Client doesn't exists or isn't subscribed.");
            } else {
                ClientUtils.unsubscribe(client);
                System.out.println("Client unsubscribed.");
            }
        }
    }
    
    public void showInventory() {
        Connection conn = null;
        
        try {
            conn = MySQLUtils.getConn();
            RoomDAO rdao = new MySQLRoomDAO(conn);
            DecorationDAO ddao = new MySQLDecorationDAO(conn);
            ClueDAO cdao = new MySQLClueDAO(conn);
            List<Room> rooms = rdao.readAll();
            List<Decoration> decorations = ddao.readAll();
            List<Clue> clues = cdao.readAll();
            
            System.out.println("Available rooms:");
            for (Room room : rooms) {
                System.out.println("id: " + room.getId() + ", " + room.getName() + ", " + room.getThematic() + ", " +
                        room.getDifficulty());
            }
            System.out.println();
            System.out.println("Available decorations:");
            for (Decoration decoration : decorations) {
                String used = "";
                if (decoration.getIdRoom() != null) {
                    used = "in use";
                } else {
                    used = "available";
                }
                System.out.println("id: " + decoration.getId() + ", " + decoration.getName() + ", " + decoration.getThematic() + ", " +
                        decoration.getValue() + ", " + used);
            }
            float totalDec = (float) decorations.stream().mapToDouble(Decoration::getValue).sum();
            System.out.println("Total value of decorations: " + totalDec);
            System.out.println();
            System.out.println("Available clues:");
            for (Clue clue : clues) {
                String used = "";
                if (clue.getIdRoom() != null) {
                    used = "in use";
                } else {
                    used = "available";
                }
                System.out.println("id: " + clue.getId() + "," + clue.getThematic() + ", " +
                        clue.getValue() + ", " + used);
            }
            float totalClues = (float) clues.stream().mapToDouble(Clue::getValue).sum();
            System.out.println("Total value of clues: " + totalClues);
            System.out.println();
            System.out.println("Total value of inventory: " + (totalDec + totalClues));
            
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    public void printCertificate() {
        Client client = null;
        Room room = null;
        while (client == null) {
            String message = "Introduce the id of the client:";
            int id = Input.readInt(message);
            client = ClientUtils.searchClient(id);
            if (client == null) {
                System.out.println("Client doesn't exist.");
            }
        }
        
        while (room == null) {
            String message = "Introduce the id of the room:";
            int id = Input.readInt(message);
            room = RoomUtils.searchRoom(id);
            if (room == null) {
                System.out.println("Room doesn't exist.");
            }
        }
        
        CertificateUtils.printCertificate (client, room);
        
    }
}

