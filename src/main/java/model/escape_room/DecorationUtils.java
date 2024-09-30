package model.escape_room;

import dao.ClientDAO;
import dao.ClueDAO;
import dao.DAOException;
import dao.DecorationDAO;
import dao.mysql.MySQLClientDAO;
import dao.mysql.MySQLClueDAO;
import dao.mysql.MySQLDecorationDAO;
import dao.mysql.MySQLUtils;
import model.clues.Clue;
import model.decorations.Decoration;
import model.rooms.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DecorationUtils {
    public static Decoration readDecoration (List<Decoration> decorations) {
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
