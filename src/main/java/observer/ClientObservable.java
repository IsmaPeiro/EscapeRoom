package observer;

import dao.DAOException;
import dao.mysql.MySQLClientDAO;
import model.clients.Client;
import model.rooms.Room;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ClientObservable {


    public void notifyClients(MySQLClientDAO clientsDao, Room room) throws DAOException {
        RoomObserver roomUpdate = new RoomObserver();
        List<Client> subscribedClients = clientsDao.getSubscribedClients();
        subscribedClients.forEach(c -> roomUpdate.update(room));
    }
}
