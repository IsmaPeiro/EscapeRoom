package model.escape_room;

import dao.ClientDAO;
import dao.DAOException;
import dao.mysql.MySQLClientDAO;
import dao.mysql.MySQLUtils;
import model.clients.Client;
import model.rooms.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClientManagement {
    private String database;
    
    public ClientManagement(String database) {
        this.database = database;
    }
    
    public void addClient() {
        Client client;
        
        client = ClientUtils.inputClient();
        
        Connection conn = null;
        
        try {
            conn = MySQLUtils.getConn(database);
            ClientDAO dao = new MySQLClientDAO(conn);
            dao.create(client);
            System.out.println("Client created.");
        } catch (DAOException | SQLException e) {
            System.err.println("Client not created.");
            e.printStackTrace();
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    public void listClients() {
        Connection conn = null;
        
        try {
            conn = MySQLUtils.getConn(database);
            ClientDAO dao = new MySQLClientDAO(conn);
            List<Client> clients = dao.readAll();
            clients.forEach(System.out::println);
        } catch (DAOException | SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    public void subscribeClient() {
        Client client;
        Connection conn = null;
        boolean exit=false;
        
        while (!exit) {
            int id = Input.readInt("Introduce the id of the client you want to subscribe or 0 to exit:");
            if (id!=0) {
                client = searchClient(id);
                if (client == null || client.isSubscribed()) {
                    System.out.println("Client doesn't exists or is already subscribed.");
                } else {
                    try {
                        conn = MySQLUtils.getConn(database);
                        ClientDAO dao = new MySQLClientDAO(conn);
                        dao.subscribeClient(client.getId());
                    } catch (DAOException | SQLException e) {
                        e.printStackTrace();
                    } finally {
                        MySQLUtils.closeConn(conn);
                    }
                    System.out.println("Client subscribed.");
                    exit=true;
                }
            } else {
                exit=true;
            }
        }
    }
    
    public void unsubscribeClient() {
        Connection conn = null;
        Client client;
        boolean exit=false;
        
        while (!exit) {
            int id = Input.readInt("Input the id of the client you want to unsubscribe or 0 to exit:");
            if (id!=0) {
                client = searchClient(id);
                if (client == null || !client.isSubscribed()) {
                    System.out.println("Client doesn't exists or isn't subscribed.");
                } else {
                    try {
                        conn = MySQLUtils.getConn(database);
                        ClientDAO dao = new MySQLClientDAO(conn);
                        dao.unsubscribeClient(client.getId());
                    } catch (DAOException | SQLException e) {
                        e.printStackTrace();
                    } finally {
                        MySQLUtils.closeConn(conn);
                    }
                    System.out.println("Client unsubscribed.");
                    exit=true;
                }
            } else {
                exit=true;
            }
        }
    }
    
    public void printCertificate(RoomManagement rm) {
        Client client = null;
        Room room = null;
        int roomId, clientId;
        boolean exit=false;
        
        while (!exit) {
            clientId = Input.readInt("Input the id of the client or 0 to exit:");
            if (clientId != 0) {
                client = searchClient(clientId);
                if (client == null) {
                    System.out.println("Client doesn't exist.");
                } else {
                    exit=true;
                }
            } else {
                exit=true;
            }
        }
        
        if (client!=null) exit=false;
        
        while (room == null && !exit) {
            roomId = Input.readInt("Input the id of the room or 0 to exit:");
            if (roomId != 0) {
                room = rm.searchRoom(roomId);
                if (room == null) {
                    System.out.println("Room doesn't exist.");
                } else {
                    exit=true;
                }
            } else {
                exit=true;
            }
        }
        
        if (client!=null&&room!=null) ClientUtils.printCertificate(client, room);
    }
    
    public Client searchClient(int id) {
        Connection conn = null;
        Client client = null;
        try {
            conn = MySQLUtils.getConn(database);
            ClientDAO dao = new MySQLClientDAO(conn);
            client = dao.readOne(id);
        } catch (DAOException | SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLUtils.closeConn(conn);
        }
        return client;
    }
}
