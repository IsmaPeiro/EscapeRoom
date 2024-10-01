package observer;

import dao.ClientDAO;
import dao.DAOException;
import dao.mysql.MySQLClientDAO;
import dao.mysql.MySQLUtils;
import model.clients.Client;
import model.rooms.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClientObservable {


    public void notifyClients(Room room) {
        RoomObserver roomUpdate = new RoomObserver();
        Connection conn = null;

        try {
            conn = MySQLUtils.getConn();
            ClientDAO dao = new MySQLClientDAO(conn);

            List<Client> subscribedClients = dao.getSubscribedClients();
            subscribedClients.forEach(c -> roomUpdate.update(room, c));
        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        } finally {
            MySQLUtils.closeConn(conn);
        }

    }
}
