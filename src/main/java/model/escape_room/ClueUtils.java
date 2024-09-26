package model.escape_room;

import model.clues.Clue;
import model.rooms.Room;
import java.util.Scanner;

public class ClueUtils {
    public static void addClue (Room room) {
        room.createClue();
    }

    public static void removeClue (Room room){
//        int id = room.getId();
//
//        Clue clue = room.findClue(id);
//        if (clue != null){
//            room.removeClueFromList(clue);
//            System.out.println("Clue removed.");
//        }else{
//            System.out.println("Nonexistent clue.");
//        }
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
