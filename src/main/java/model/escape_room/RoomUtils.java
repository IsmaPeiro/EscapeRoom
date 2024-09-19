package model.escape_room;

import factory.*;
import model.clues.Clue;
import model.decorations.Decoration;
import model.rooms.Difficulty;
import model.rooms.Room;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomUtils {
    public static Room addRoom() {
        int id;
        String name;
        Difficulty difficulty;
        List<Clue> clues=null;
        List<Decoration> decorations=null;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("What thematic have the Room?");
        RoomAbstractFactory factory = choseThematic();
        System.out.println("Id:");
        id=sc.nextInt();
        sc.nextLine();
        System.out.println("Name:");
        name = sc.nextLine();
        System.out.println("Difficulty:");
        difficulty=choseDifficulty();
        
        return factory.createRoom(id, name, difficulty, clues, decorations);
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
}
