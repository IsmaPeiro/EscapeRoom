package model.rooms;

import model.clues.Clue;
import model.decorations.Decoration;
import model.escape_room.Thematic;

import java.util.List;
import java.util.Scanner;

public class FantasticRoom extends Room {
    public FantasticRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(id, name, difficulty, clues, decorations);
        thematic=Thematic.FANTASTIC;
    }

    @Override
    public Decoration createDecoration(){


    }

    public String getName(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Write the name of the decoration yoo want to add:");
        String name = sc.nextLine();
        return name;
    }


    
    @Override
    public String toString() {
        return "FantasticRoom{} " + super.toString();
    }
}
