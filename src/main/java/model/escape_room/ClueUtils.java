package model.escape_room;

import dao.ClueDAO;
import dao.DAOException;
import dao.mysql.MySQLClueDAO;
import dao.mysql.MySQLUtils;
import model.clues.Clue;
import model.decorations.Decoration;
import model.rooms.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClueUtils {
    public static Clue readClue (List<Clue> clues) {
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
    
    public static Room selectRoom (RoomManagement rm) {
        Room room = null;
        int roomId;
        boolean exit=false;
        
        while (!exit) {
            roomId = RoomUtils.selectRoom();
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
