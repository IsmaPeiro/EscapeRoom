package model.escape_room;

import dao.DAOException;
import dao.DecorationDAO;
import dao.mysql.MySQLDecorationDAO;
import dao.mysql.MySQLUtils;
import factory.RoomAbstractFactory;
import model.decorations.Decoration;
import model.rooms.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DecorationManagement {
    private Decoration selectDecoration(Room room) {
        Decoration decoration;
        Connection conn = null;
        List<Decoration> decorations = null;
        try {
            conn = MySQLUtils.getConn();
            DecorationDAO dao = new MySQLDecorationDAO(conn);
            decorations = dao.readAvaiable(room.getThematic());
        } catch (DAOException | SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLUtils.closeConn(conn);
        }
        decoration = readDecoration(decorations);
        
        return decoration;
    }
    
    public void addDecorationToRoom(RoomManagement rm) {
        Room room;
        room=selectRoom(rm);
        if (room!=null) saveToDB(room);
    }
    
    private void saveToDB(Room room) {
        Decoration decoration;
        decoration = selectDecoration(room);
        if (decoration != null) {
            decoration.setIdRoom(room.getId());
            
            Connection conn = null;
            
            try {
                conn = MySQLUtils.getConn();
                DecorationDAO dao = new MySQLDecorationDAO(conn);
                dao.update(decoration);
                System.out.println("Decoration added.");
            } catch (DAOException | SQLException e) {
                e.printStackTrace();
            } finally {
                MySQLUtils.closeConn(conn);
            }
        }
    }
    
    public void removeDecorationRoom(RoomManagement rm) {
        Room room;
        room=selectRoom(rm);
        if (room!=null) removeFromDB(room);
    }
    
    public void removeFromDB(Room room) {
        Decoration decoration;
        
        List<Decoration> decorations = room.getDecorations();
        decoration = readDecoration(decorations);
        
        if (decoration != null) {
            Connection conn = null;
            
            try {
                conn = MySQLUtils.getConn();
                DecorationDAO dao = new MySQLDecorationDAO(conn);
                dao.setRommToNull(decoration);
                System.out.println("Decoration removed.");
            } catch (DAOException | SQLException e) {
                e.printStackTrace();
            } finally {
                MySQLUtils.closeConn(conn);
            }
        }
    }
    
    private Decoration readDecoration (List<Decoration> decorations) {
        Decoration decoration=null;
        
        if (!decorations.isEmpty()) {
            decorations.forEach(System.out::println);
            while (decoration == null) {
                int clueID = Input.readInt("Insert the id of decoration:");
                decoration = decorations.stream().filter(d -> d.getId() == clueID).findFirst().orElse(null);
                if (decoration == null) System.out.println("Invalid Decoration.");
            }
        } else {
            System.out.println("No decorations available.");
        }
        return decoration;
    }
    
    private Room selectRoom (RoomManagement rm) {
        Room room = null;
        int roomId;
        boolean exit=false;
        
        while (!exit) {
            roomId = Input.readInt("Input the id of the room or 0 to exit:");
            if (roomId != 0) {
                room = rm.searchRoom(roomId);
                if (room!=null) exit=true;
            } else {
                exit=true;
            }
        }
        return room;
    }
    
    public void newDecoration() {
        String name;
        String material;
        float value;
        Decoration decoration;
        Connection conn = null;
        
        System.out.println("What theme has the decoration?");
        RoomAbstractFactory factory = RoomUtils.choseThematic();
        name = Input.readString("Name:");
        material = Input.readString("Material:");
        value = Input.readFloat("Value:");
        decoration = factory.createDecoration(name, material, value);
        
        try {
            conn = MySQLUtils.getConn();
            DecorationDAO dao = new MySQLDecorationDAO(conn);
            dao.buy(decoration);
            System.out.println("Decoration bought.");
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
}