package model.escape_room;

import dao.ClientDAO;
import dao.DAOException;
import dao.RoomDAO;
import dao.TicketDAO;
import dao.mysql.MySQLClientDAO;
import dao.mysql.MySQLRoomDAO;
import dao.mysql.MySQLTcketDAO;
import dao.mysql.MySQLUtils;
import model.clients.Client;
import model.clients.Ticket;
import model.rooms.Room;

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
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    public void showAllRooms() {
        // rooms.forEach(System.out::println);
        Connection conn = null;
        
        try {
            conn = MySQLUtils.getConn();
            RoomDAO dao = new MySQLRoomDAO(conn);
            List<Room> rooms = dao.readAll();
            rooms.forEach(System.out::println);
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    public void addClueToRoom() {
        Room room = searchRoom();
        ClueUtils.addClue(room);
    }
    
    public void addDecorationToRoom() {
        Room room = searchRoom();
        DecorationUtils.addDecoration(room);
    }
    
    private Room searchRoom() {
        int id;
        Room room = null;
        boolean found = false;
        int i = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Id:");
        id = sc.nextInt();
        room = rooms.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
        return room;
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
    
    public void listTickets() {
        Connection conn = null;
        try {
            conn = MySQLUtils.getConn();
            TicketDAO dao = new MySQLTcketDAO(conn);
            List<Ticket> tickets = dao.readAll();
            tickets.forEach(System.out::println);
            
            System.out.println("Total value of tickets: "+
                    tickets.stream().mapToDouble(Ticket::getValue).sum());
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
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
}
