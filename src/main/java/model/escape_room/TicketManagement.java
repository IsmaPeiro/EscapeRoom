package model.escape_room;

import dao.DAOException;
import dao.TicketDAO;
import dao.mysql.MySQLTcketDAO;
import dao.mysql.MySQLUtils;
import model.clients.Ticket;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TicketManagement {
    public void createTicket(RoomManagement rm, ClientManagement clm) {
        Ticket ticket=TicketUtils.inputTicket(rm, clm);
        
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
}
