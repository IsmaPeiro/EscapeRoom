package model.escape_room;

import dao.ClueDAO;
import dao.DAOException;
import dao.DecorationDAO;
import dao.mysql.MySQLClueDAO;
import dao.mysql.MySQLDecorationDAO;
import dao.mysql.MySQLUtils;
import model.clues.Clue;
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
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
        decoration = DecorationUtils.readDecoration(decorations);
        
        return decoration;
    }
    
    public void addDecorationToRoom(RoomManagement rm) {
        Room room = null;
        room=DecorationUtils.selectRoom(rm);
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
            } catch (DAOException | SQLException e) {
                System.out.println(e);
            } finally {
                MySQLUtils.closeConn(conn);
            }
        }
    }
    
    public void removeDecorationRoom(RoomManagement rm) {
        Room room;
        room=ClueUtils.selectRoom(rm);
        if (room!=null) removeFromDB(room);
    }
    
    public void removeFromDB(Room room) {
        Decoration decoration;
        
        List<Decoration> decorations = room.getDecorations();
        decoration = DecorationUtils.readDecoration(decorations);
        
        if (decoration != null) {
            Connection conn = null;
            
            try {
                conn = MySQLUtils.getConn();
                DecorationDAO dao = new MySQLDecorationDAO(conn);
                dao.setRommToNull(decoration);
            } catch (DAOException | SQLException e) {
                System.out.println(e);
            } finally {
                MySQLUtils.closeConn(conn);
            }
        }
    }
}