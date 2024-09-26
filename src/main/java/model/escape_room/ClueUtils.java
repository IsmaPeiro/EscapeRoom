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
    public static Clue addClue(Room room) {
        Clue clue = null;
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
        if (!clues.isEmpty()) {
            clues.forEach(System.out::println);
            while (clue == null) {
                int decorationID = Input.readInt("Insert the id of decoration:");
                clue = clues.stream().filter(d -> d.getId() == decorationID).findFirst().orElse(null);
                if (clue == null) System.out.println("Invalid Decoration");
            }
        } else {
            System.out.println("Not clues available.");
        }
        return clue;
    }
    
    public static Clue removeClue (Room room) {
        Clue clue = null;
        Connection conn = null;
        List<Clue> clues = room.getClues();
        
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
}
