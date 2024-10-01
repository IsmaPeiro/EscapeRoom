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
            e.printStackTrace();
        } finally {
            MySQLUtils.closeConn(conn);
        }
        clue = readClue(clues);
        
        return clue;
    }
    
    public void addClueToRoom(RoomManagement rm) {
        Room room;
        room=selectRoom(rm);
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
                System.out.println("Clue added.");
            } catch (DAOException | SQLException e) {
                e.printStackTrace();
            } finally {
                MySQLUtils.closeConn(conn);
            }
        }
    }
    
    public void removeClueRoom(RoomManagement rm) {
        Room room;
        room=selectRoom(rm);
        if (room!=null) removeFromDB(room);
    }
    
    public void removeFromDB(Room room) {
        Clue clue;
        
        List<Clue> clues = room.getClues();
        clue = readClue(clues);
        
        if (clue != null) {
            Connection conn = null;
            
            try {
                conn = MySQLUtils.getConn();
                ClueDAO dao = new MySQLClueDAO(conn);
                dao.setRommToNull(clue);
                System.out.println("Clue removed.");
            } catch (DAOException | SQLException e) {
                e.printStackTrace();
            } finally {
                MySQLUtils.closeConn(conn);
            }
        }
    }
    
    private Clue readClue (List<Clue> clues) {
        Clue clue=null;
        
        if (!clues.isEmpty()) {
            clues.forEach(System.out::println);
            while (clue == null) {
                int clueID = Input.readInt("Insert the id of clue:");
                clue = clues.stream().filter(d -> d.getId() == clueID).findFirst().orElse(null);
                if (clue == null) System.out.println("Invalid Clue.");
            }
        } else {
            System.out.println("No clues available.");
        }
        return clue;
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
}
