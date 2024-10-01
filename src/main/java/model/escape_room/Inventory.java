package model.escape_room;

import dao.*;
import dao.mysql.*;
import model.clues.Clue;
import model.decorations.Decoration;
import model.rooms.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Inventory {
    
    public void showInventory() {
        Connection conn = null;
        
        try {
            conn = MySQLUtils.getConn();
            RoomDAO rdao = new MySQLRoomDAO(conn);
            DecorationDAO ddao = new MySQLDecorationDAO(conn);
            ClueDAO cdao = new MySQLClueDAO(conn);
            List<Room> rooms = rdao.readAll();
            List<Decoration> decorations = ddao.readAll();
            List<Clue> clues = cdao.readAll();
            
            System.out.println("Available rooms:");
            for (Room room : rooms) {
                System.out.println("id: " + room.getId() + ", " + room.getName() + ", " + room.getThematic() + ", " +
                        room.getDifficulty());
            }
            System.out.println();
            System.out.println("Available decorations:");
            for (Decoration decoration : decorations) {
                String used = "";
                if (decoration.getIdRoom() != null) {
                    used = "in use";
                } else {
                    used = "available";
                }
                System.out.println("id: " + decoration.getId() + ", " + decoration.getName() + ", " + decoration.getThematic() + ", " +
                        decoration.getValue() + ", " + used);
            }
            float totalDec = (float) decorations.stream().mapToDouble(Decoration::getValue).sum();
            System.out.println("Total value of decorations: " + totalDec);
            System.out.println();
            System.out.println("Available clues:");
            for (Clue clue : clues) {
                String used = "";
                if (clue.getIdRoom() != null) {
                    used = "in use";
                } else {
                    used = "available";
                }
                System.out.println("id: " + clue.getId() + "," + clue.getThematic() + ", " +
                        clue.getValue() + ", " + used);
            }
            float totalClues = (float) clues.stream().mapToDouble(Clue::getValue).sum();
            System.out.println("Total value of clues: " + totalClues);
            System.out.println();
            System.out.println("Total value of inventory: " + (totalDec + totalClues));
            
        } catch (DAOException | SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
    
    
}

