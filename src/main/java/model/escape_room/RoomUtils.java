package model.escape_room;

import dao.DAOException;
import dao.RoomDAO;
import dao.mysql.MySQLRoomDAO;
import dao.mysql.MySQLUtils;
import factory.*;
import model.clues.Clue;
import model.decorations.Decoration;
import model.rooms.Difficulty;
import model.rooms.Room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomUtils {
    public static Room addRoom() {
        String name;
        Difficulty difficulty;
        List<Clue> clues=null;
        List<Decoration> decorations=null;
        
        System.out.println("What thematic have the Room?");
        RoomAbstractFactory factory = choseThematic();
        name=Input.readString("Name:");
        System.out.println("Difficulty:");
        difficulty=choseDifficulty();
        
        return factory.createRoom(name, difficulty, clues, decorations);
    }
    
    private static Difficulty choseDifficulty() {
        Scanner sc = new Scanner(System.in);
        byte option = -1;
        final int MINIMUM = 1;
        final int MAXIMUM = Difficulty.values().length;
        
        do {
            
            AtomicInteger index = new AtomicInteger(1);
            Arrays.stream(Difficulty.values()).forEach(d -> System.out.println(index.getAndIncrement() + ". " + d));
            
            if (sc.hasNextByte()) option = sc.nextByte();
            sc.nextLine();
            if (option < MINIMUM || option > MAXIMUM) {
                System.out.println("Chose a valid option.");
            }
        } while (option < MINIMUM || option > MAXIMUM);
        
        Difficulty difficulty = Difficulty.values()[option - 1];
        
        switch (difficulty) {
            case EASY -> {
                return Difficulty.EASY;
            }
            case MEDIUM -> {
                return Difficulty.MEDIUM;
            }
            case HARD -> {
                return Difficulty.HARD;
            }
        }
        return null;
    }
    
    private static RoomAbstractFactory choseThematic() {
        Scanner sc = new Scanner(System.in);
        byte option = -1;
        final int MINIMUM = 1;
        final int MAXIMUM = Thematic.values().length;
        
        do {
            
            AtomicInteger index = new AtomicInteger(1);
            Arrays.stream(Thematic.values()).forEach(t -> System.out.println(index.getAndIncrement() + ". " + t));
            
            if (sc.hasNextByte()) option = sc.nextByte();
            sc.nextLine();
            if (option < MINIMUM || option > MAXIMUM) {
                System.out.println("Chose a valid option.");
            }
        } while (option < MINIMUM || option > MAXIMUM);
        
        Thematic thematic = Thematic.values()[option - 1];
        
        switch (thematic) {
            case FANTASTIC -> {
                return new FantasticRoomFactory();
            }
            case MEDIEVAL -> {
                return new MedievalRoomFactory();
            }
            
            case SCIFI -> {
                return new SciFiRoomFactory();
            }
            
            case TERROR -> {
                return new TerrorRoomFactory();
            }
        }
        return null;
    }
    
    public static Room searchRoom (int id) {
        Connection conn = null;
        Room room = null;
        try {
            conn = MySQLUtils.getConn();
            RoomDAO dao = new MySQLRoomDAO(conn);
            room = dao.readOne(id);
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
        return room;
    }
    
    public static float calculateValue (Room room) {
        float total=0;
        total+=(float)room.getClues().stream().mapToDouble(Clue::getValue).sum();
        total+=(float)room.getDecorations().stream().mapToDouble(Decoration::getValue).sum();
        return total;
    }
}
