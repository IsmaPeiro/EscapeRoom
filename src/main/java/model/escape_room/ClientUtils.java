package model.escape_room;

import dao.ClientDAO;
import dao.DAOException;
import dao.mysql.MySQLClientDAO;
import dao.mysql.MySQLUtils;
import model.clients.Client;
import java.sql.Connection;
import java.sql.SQLException;

public class ClientUtils {
    public static Client addClient() {
        String name, surname;
        boolean subscribed;
        
        name=Input.readString("Input the name:");
        surname=Input.readString("Input the surname");
        subscribed=Input.readYesNo("Want to subscribe?");
        
        return new Client(name, surname, subscribed);
    }
    
    public static Client searchClient (int id) {
        Connection conn = null;
        Client client = null;
        try {
            conn = MySQLUtils.getConn();
            ClientDAO dao = new MySQLClientDAO(conn);
            client = dao.readOne(id);
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
        return client;
    }
}
