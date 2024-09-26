package dao.mysql;

import dao.DAOException;
import dao.TicketDAO;
import model.clients.Client;
import model.clients.Ticket;
import model.rooms.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLTcketDAO implements TicketDAO {
    
    final String INSERT = "INSERT INTO tickets (idclient, value, idroom) " +
            "VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE tickets SET idclient = ?, value = ?, idroom = ? " +
            "WHERE idtickets = ?";
    final String DELETE = "DELETE FROM tickets WHERE idtickets = ?";
    final String GETALL = "SELECT * FROM tickets";
    final String GETONE = "SELECT * FROM tickets " +
            "WHERE idtickets = ?";
    
    private Connection conn;
    
    public MySQLTcketDAO(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void create(Ticket ticket) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        
        try {
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setInt(1, ticket.getClient().getId());
            stat.setFloat(2, ticket.getValue());
            stat.setInt(3, ticket.getRoom().getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have been saved");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                ticket.setId(rs.getInt(1));
            } else {
                throw new DAOException("This ID cannot be assigned to this ticket.");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
    }
    
    private Ticket convert(ResultSet rs) throws SQLException, DAOException {
        
        MySQLClientDAO mySQLClientDAO = new MySQLClientDAO(conn);
        Client client = mySQLClientDAO.readOne(rs.getInt("idclient"));
        float value = rs.getFloat("value");
        MySQLRoomDAO mySQLRoomDAO = new MySQLRoomDAO(conn);
        Room room = null;
        try {
            room = mySQLRoomDAO.readOne(rs.getInt("idroom"));
        } catch (DAOException e) {
            System.out.println("ticket id: "+rs.getInt("idtickets")+ " room was deleted");
        }
        Ticket ticket = new Ticket(client, value, room);
        ticket.setId(rs.getInt("idtickets"));
        return ticket;
    }
    
    @Override
    public Ticket readOne(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Ticket ticket = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                ticket = convert(rs);
            } else {
                throw new DAOException("Record not found.");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return ticket;
    }
    
    @Override
    public List<Ticket> readAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Ticket> tickets = new ArrayList<>();
        
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                tickets.add(convert(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return tickets;
    }
    
    @Override
    public void update(Ticket ticket) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(UPDATE);
            
            stat.setInt(1, ticket.getClient().getId());
            stat.setFloat(2, ticket.getValue());
            stat.setInt(3, ticket.getRoom().getId());
            stat.setInt(4, ticket.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have been modified");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
        }
    }
    
    @Override
    public void delete(Ticket ticket) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, ticket.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have been deleted");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
        }
    }
}
    




