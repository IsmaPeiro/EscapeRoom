package model.escape_room;

import model.clues.Clue;
import model.rooms.Room;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLException;
import java.util.Scanner;

public class ClueUtils {
    public static Clue addClue (Room room) {
        Clue clue = null;
        Connection conn = null;
        List<Clue> clues = null;
        try{
            conn = MySQLUtils.getConn();
            ClueDAO dao = new MySQLClueDAO(conn);
            clues = dao.readAvailable(room.getThematic());
        }catch (DAOException | SQLException e){
            System.out.println(e);
        }finally {
            MySQLUtils.closeConn(conn);
        }
        if (!clues.isEmpty()){
            clues.forEach(System.out::println);
            while (clue == null){
                int clueID = Input.readInt("Insert the id of the clue:");
                clue = clues.stream().filter(c -> c.getId == clueID).findFirst().orElse(null);
                if(clue == null) System.out.println("Invalid clue");
            }
        }else{
            System.out.println("No clues available.");
        }
        return clue;
    }

    public static void removeClue (Room room){
        Clue clue = null;
        Connection conn = null;
        List<Clue> clues = room.getClues();

        if(!clues.isEmpty()){
            clues.forEach(System.out::println);
            while (clue == null) {
                int clueID = Input.readInt("Insert the id of the clue:");
                clue = clues.stream().filter(c -> c.getId() == clueID).findFirst().orElse(null);
                if (clue == null) System.out.println("Invalid clue.");
            }
        }else{
            System.out.println("No clues available.");
        }
        return clue;
    }

    public static float getClueScore(){
        Scanner sc = new Scanner(System.in);
        System.out.println("What's the score of the clue to add?");
        float score = sc.nextFloat();

        return score;
    }

    public static String getClueDifficulty(){
        Scanner sc = new Scanner(System.in);
        System.out.println("What's the difficulty of the clue to add?");
        String difficulty = sc.next();

        return difficulty;
    }

    public static float getClueValue(){
        Scanner sc = new Scanner(System.in);
        System.out.println("What's the value of the clue to add?");
        float value = sc.nextFloat();

        return value;
    }

}
