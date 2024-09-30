package dao.mysql;

import dao.ClientDAO;
import dao.DAOException;
import model.clients.Client;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLClientDAO implements ClientDAO {
    
    final String INSERT = "INSERT INTO clients (name, surname, subscribed) " +
            "VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE clients SET name = ?, surname = ?, subscribed = ? " +
            "WHERE idclients = ?";
    final String DELETE = "DELETE FROM clients WHERE idclients = ?";
    final String GETALL = "SELECT * FROM clients";
    final String GETONE = "SELECT * FROM clients " +
            "WHERE idclients = ?";
    final String GETSUBSCRIBED = "SELECT * FROM clients " +
            "WHERE subscribed = true";
    
    private  Connection conn;
    
    public MySQLClientDAO(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void create(Client client) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        
        try {
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, client.getName());
            stat.setString(2, client.getSurname());
            stat.setBoolean(3, client.isSubscribed());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have been saved");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                client.setId(rs.getInt(1));
            } else {
                throw new DAOException("This ID cannot be assigned to this client.");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
    }
    
    private Client convert(ResultSet rs) throws SQLException, DAOException {
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        boolean subscribed = rs.getBoolean("subscribed");
        Client client = new Client(name, surname, subscribed);
        client.setId(rs.getInt("idclients"));
        return client;
    }
    
    @Override
    public Client readOne(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Client client = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                client = convert(rs);
            } else {
                throw new DAOException("Record not found.");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return client;
    }
    
    @Override
    public List<Client> readAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Client> clients = new ArrayList<>();
        
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                clients.add(convert(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return clients;
    }
    
    @Override
    public void update(Client client) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(UPDATE);
            
            stat.setString(1, client.getName());
            stat.setString(2, client.getSurname());
            stat.setBoolean(3, client.isSubscribed());
            stat.setInt(4, client.getId());
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
    public void delete(Client client) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, client.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have been deleted");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
        }
    }

    public List<Client> getSubscribedClients() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Client> subscribedClients = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETSUBSCRIBED);
            rs = stat.executeQuery();
            while (rs.next()) {
                subscribedClients.add(convert(rs));
            }
        }catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return subscribedClients;
    }
}

