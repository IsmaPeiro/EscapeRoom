package model.escape_room;

import dao.DAOException;
import dao.RoomDAO;
import dao.mysql.MySQLRoomDAO;
import dao.mysql.MySQLUtils;
import model.rooms.Room;
import observer.ClientObservable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoomManagement {
    
    public void addRoom() {
        Room room = RoomUtils.inputRoom();
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
    
    public void removeRoom() {
        int roomId=RoomUtils.selectRoom();
        Room room = null;
        
        if (roomId != 0 && !Input.readYesNo("Are you sure?")) {
            room = null;
        } else {
            room=searchRoom(roomId);
        }
        
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
    
    public Room searchRoom(int id) {
        Connection conn = null;
        Room room = null;
        try {
            conn = MySQLUtils.getConn();
            RoomDAO dao = new MySQLRoomDAO(conn);
            room = dao.readOne(id);
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
        return room;
    }
}
