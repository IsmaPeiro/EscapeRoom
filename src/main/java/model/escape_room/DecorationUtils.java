package model.escape_room;

import dao.ClientDAO;
import dao.ClueDAO;
import dao.DAOException;
import dao.DecorationDAO;
import dao.mysql.MySQLClientDAO;
import dao.mysql.MySQLClueDAO;
import dao.mysql.MySQLDecorationDAO;
import dao.mysql.MySQLUtils;
import factory.RoomAbstractFactory;
import model.clues.Clue;
import model.decorations.Decoration;
import model.rooms.Difficulty;
import model.rooms.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DecorationUtils {
    
    public static Decoration addDecoration(Room room) {
        Decoration decoration = null;
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
        if (!decorations.isEmpty()) {
            decorations.forEach(System.out::println);
            while (decoration == null) {
                int decorationID = Input.readInt("Insert the id of decoration:");
                decoration = decorations.stream().filter(d -> d.getId() == decorationID).findFirst().orElse(null);
                if (decoration == null) System.out.println("Invalid Decoration");
            }
        } else {
            System.out.println("Not decorations available.");
        }
        return decoration;
    }
    
    public static Decoration removeDecoration(Room room) {
        Decoration decoration = null;
        Connection conn = null;
        List<Decoration> decorations = room.getDecorations();
        
        if (!decorations.isEmpty()) {
            decorations.forEach(System.out::println);
            while (decoration == null) {
                int decorationID = Input.readInt("Insert the id of decoration:");
                decoration = decorations.stream().filter(d -> d.getId() == decorationID).findFirst().orElse(null);
                if (decoration == null) System.out.println("Invalid Decoration");
            }
        } else {
            System.out.println("No decorations available.");
        }
        return decoration;
    }

    public static Decoration newDecoration() {
        String name;
        String material;
        float value;

        System.out.println("What theme has the decoration?");
        RoomAbstractFactory factory = RoomUtils.choseThematic();
        name = Input.readString("Name:");
        material = Input.readString("Material:");
        value = Input.readFloat("Value:");

        return factory.createDecoration(name, material, value);
    }
}
