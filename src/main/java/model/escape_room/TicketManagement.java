package model.escape_room;

import dao.DAOException;
import dao.TicketDAO;
import dao.mysql.MySQLTcketDAO;
import dao.mysql.MySQLUtils;
import model.clients.Client;
import model.clients.Ticket;
import model.rooms.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TicketManagement {
    private String database;
    
    public TicketManagement(String database) {
        this.database = database;
    }
    
    public void createTicket(RoomManagement rm, ClientManagement clm) {
        Ticket ticket=inputTicket(rm, clm);
        
        Connection conn = null;
        
        try {
            conn = MySQLUtils.getConn(database);
            TicketDAO dao = new MySQLTcketDAO(conn);
            dao.create(ticket);
            System.out.println(ticket);
        } catch (DAOException | SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    public void listTickets() {
        Connection conn = null;
        try {
            conn = MySQLUtils.getConn(database);
            TicketDAO dao = new MySQLTcketDAO(conn);
            List<Ticket> tickets = dao.readAll();
            tickets.forEach(System.out::println);
            
            System.out.println("Total value of tickets: " +
                    tickets.stream().mapToDouble(Ticket::getValue).sum());
        } catch (DAOException | SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    private Ticket inputTicket(RoomManagement rm, ClientManagement clm) {
        int clientId, roomId;
        float value;
        Client client = null;
        Room room = null;
        
        while (client == null) {
            clientId = Input.readInt("Input the id of the client:");
            client = clm.searchClient(clientId);
        }
        
        while (room == null) {
            roomId = Input.readInt("Input the id of the room:");
            room = rm.searchRoom(roomId);
        }
        
        value=50+(RoomUtils.calculateValue(room)*0.10F);
        
        return new Ticket (client, value, room);
    }
}
