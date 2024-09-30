package model.escape_room;

import dao.ClueDAO;
import dao.DAOException;
import dao.mysql.MySQLClueDAO;
import dao.mysql.MySQLUtils;
import model.clues.Clue;
import model.rooms.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClueManagement {
    
    private Clue selectClue(Room room) {
        Clue clue;
        Connection conn = null;
        List<Clue> clues = null;
        try {
            conn = MySQLUtils.getConn();
            ClueDAO dao = new MySQLClueDAO(conn);
            clues = dao.readAvaiable(room.getThematic());
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
        clue = ClueUtils.readClue(clues);
        
        return clue;
    }
    
    public void addClueToRoom(RoomManagement rm) {
        Room room = null;
        room=ClueUtils.selectRoom(rm);
        if (room!=null) saveToDB(room);
    }
    
    private void saveToDB(Room room) {
        Clue clue;
        clue = selectClue(room);
        if (clue != null) {
            clue.setIdRoom(room.getId());
            
            Connection conn = null;
            
            try {
                conn = MySQLUtils.getConn();
                ClueDAO dao = new MySQLClueDAO(conn);
                dao.update(clue);
            } catch (DAOException | SQLException e) {
                System.out.println(e);
            } finally {
                MySQLUtils.closeConn(conn);
            }
        }
    }
    
    public void removeClueRoom(RoomManagement rm) {
        Room room = null;
        room=ClueUtils.selectRoom(rm);
        if (room!=null) removeFromDB(room);
    }
    
    public void removeFromDB(Room room) {
        Clue clue;
        
        List<Clue> clues = room.getClues();
        clue = ClueUtils.readClue(clues);
        
        if (clue != null) {
            Connection conn = null;
            
            try {
                conn = MySQLUtils.getConn();
                ClueDAO dao = new MySQLClueDAO(conn);
                dao.setRommToNull(clue);
            } catch (DAOException | SQLException e) {
                System.out.println(e);
            } finally {
                MySQLUtils.closeConn(conn);
            }
        }
    }
}
